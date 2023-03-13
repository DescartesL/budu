package com.eula.budu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eula.budu.DTO.EmailLoginDTO;
import com.eula.budu.DTO.EmailRegisterDTO;
import com.eula.budu.DTO.LoginDTO;
import com.eula.budu.common.*;
import com.eula.budu.entity.User;
import com.eula.budu.entity.UserAuth;
import com.eula.budu.entity.WebConfig;
import com.eula.budu.enums.LoginTypeEnum;
import com.eula.budu.enums.UserStateEnum;
import com.eula.budu.exception.BusinessException;
import com.eula.budu.mapper.UserAuthMapper;
import com.eula.budu.service.*;
import com.eula.budu.VO.UserInfoVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eula.budu.utils.AesEncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import java.util.regex.Pattern;

import static com.eula.budu.common.ResultConstants.*;
import static com.eula.budu.common.SqlConstants.LIMIT_ONE;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    private final IUserService userService;

    private final RedisService redisService;

    private final IWebConfigService webConfigService;

    private final EmailService emailService;


    /**
     * 邮箱注册
     *
     * @param vo 邮箱注册vo
     * @return ResponseResult
     */
    @Override
    public ResponseResult emailRegister(EmailRegisterDTO vo) {
        // 检查邮箱格式是否合法
        checkEmail(vo.getEmail());
        // 验证验证码
        checkCode(RedisConstants.EMAIL_CODE + vo.getEmail(), vo.getCode());
        User user = getByUserName(vo.getEmail());
        Assert.isNull(user, EMAIL_IS_EXIST.getDesc());
        // 主要用于给用户提供一个默认的头像
        WebConfig config = webConfigService.getOne(new QueryWrapper<WebConfig>().last(LIMIT_ONE));
        UserAuth auth = UserAuth.builder()
                                        .email(vo.getEmail())
                                        .avatar(config.getTouristAvatar())
                                        .nickname(vo.getNickName())
                                        .build();
        baseMapper.insert(auth);
        user = User.builder()
                            .username(vo.getEmail())
                            .roleId(Constants.USER_ROLE_ID)
                            .userAuthId(auth.getId())
                            .loginType(LoginTypeEnum.EMAIL.getType())
                            .password(AesEncryptUtils.aesEncrypt(vo.getPassword())).build();
        boolean insert = userService.save(user);
        // 删除redis中的验证码
        redisService.deleteObject(RedisConstants.EMAIL_CODE + vo.getEmail());
        return insert ? ResponseResult.success("注册成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());
    }

    /**
     * 修改密码
     * @param vo 邮箱注册vo
     * @return 返回修改的消息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updatePassword(EmailRegisterDTO vo) {
        checkEmail(vo.getEmail());
        checkCode(RedisConstants.EMAIL_CODE + vo.getEmail(), vo.getCode());
        User user = getByUserName(vo.getEmail());
        Assert.notNull(user, ERROR_MUST_REGISTER.getDesc());
        // 设置加密密码
        user.setPassword(AesEncryptUtils.aesEncrypt(vo.getPassword()));
        boolean update = userService.updateById(user);

        redisService.deleteObject(RedisConstants.EMAIL_CODE + vo.getEmail());

        return update ? ResponseResult.success("修改成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());

    }

    @Override
    public ResponseResult sendEmailCode(String email) {
        try{
            emailService.sendCode(email);
            return ResponseResult.success("验证码已经发送成功，请前往邮箱查看");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error(ERROR_DEFAULT.getDesc());
        }
    }


    /**
     * 邮箱登录
     * @param vo 前端登录的vo
     * @return 登录成功的Response
     */
    @Override
    public ResponseResult emailLogin(EmailLoginDTO vo) {
        checkEmail(vo.getEmail());
        // 检查用户是否存在
        User user = getByUserName(vo.getEmail());
        if(user == null){
            throw new BusinessException(ResultConstants.ERROR_MUST_REGISTER.getDesc());
        }
        Assert.isTrue(user.getStatus() == UserStateEnum.normal.code,EMAIL_DISABLE_LOGIN.getDesc());

        boolean validate = AesEncryptUtils.validate(user.getPassword(),vo.getPassword());
        Assert.isTrue(validate,ERROR_PASSWORD.getDesc());

        UserAuth auth = baseMapper.selectById(user.getUserAuthId());

        //登录
        StpUtil.login(user.getId().longValue());

        //组装数据
        UserInfoVO userInfoVO = UserInfoVO.builder()
                                                .id(user.getId())
                                                .userInfoId(auth.getId())
                                                .avatar(auth.getAvatar())
                                                .nickname(auth.getNickname())
                                                .intro(auth.getIntro())
                                                .webSite(auth.getWebSite())
                                                .email(user.getUsername())
                                                .loginType(user.getLoginType())
                                                .token(StpUtil.getTokenValue())
                                                .build();

        return ResponseResult.success(userInfoVO);
    }




    @Override
    public RequestBody login(LoginDTO vo) {
        return null;
    }


    //==================工具方法==========================
    private void checkEmail(@NotBlank(message = "邮箱不能为空！") String email) {
        String regex = "\\w+@{1}\\w+\\.{1}\\w+";
        Boolean matches = Pattern.compile(regex).matcher(email).matches();
        Assert.isTrue(matches, EMAIL_ERROR.getDesc());

    }

    private void checkCode(String key, String sourCode) {
        Object code = redisService.getCacheObject(key);
        Assert.isTrue(code != null && code.equals(sourCode), ERROR_EXCEPTION_CODE.getDesc());
    }

    private User getByUserName(@NotBlank(message = "邮箱不能为空！") String username) {
        return userService.getOne(new QueryWrapper<User>().eq(SqlConstants.USERNAME, username));
    }


}
