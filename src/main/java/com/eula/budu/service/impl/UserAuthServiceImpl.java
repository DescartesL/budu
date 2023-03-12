package com.eula.budu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.eula.budu.DTO.EmailLoginDTO;
import com.eula.budu.DTO.EmailRegisterDTO;
import com.eula.budu.DTO.LoginDTO;
import com.eula.budu.common.ResponseResult;
import com.eula.budu.common.ResultStatus;
import com.eula.budu.entity.User;
import com.eula.budu.entity.UserAuth;
import com.eula.budu.exception.BusinessException;
import com.eula.budu.mapper.UserAuthMapper;
import com.eula.budu.service.IUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    @Override
    public ResponseResult emailLogin(EmailLoginDTO vo) {
//        // 检查邮箱
//        checkEmail(vo.getEmail());
//        User user = getByUserName(vo.getEmail());
//        if(user == null){
//            throw new BusinessException(ResultStatus.ERROR_MUST_REGISTER.getDesc());
//        }
//        Assert.isTrue(user.getStatus() == UserStatusEnum.normal.code,EMAIL_DISABLE_LOGIN.getDesc());
//
//        boolean validate = AesEncryptUtils.validate(user.getPassword(),vo.getPassword());
//        Assert.isTrue(validate,ERROR_PASSWORD.getDesc());
//
//        UserAuth auth = baseMapper.selectById(user.getUserAuthId());
//
//        //登录
//        StpUtil.login(user.getId().longValue());
//
//        //组装数据
//        UserInfoVO userInfoVO = UserInfoVO.builder().id(user.getId()).userInfoId(auth.getId()).avatar(auth.getAvatar()).nickname(auth.getNickname())
//                .intro(auth.getIntro()).webSite(auth.getWebSite()).email(user.getUsername()).loginType(user.getLoginType()).token(StpUtil.getTokenValue()).build();
//
//        return ResponseResult.success(userInfoVO);
        return null;
    }



    @Override
    public RequestBody login(LoginDTO vo) {
        return null;
    }

    @Override
    public ResponseResult emailRegister(EmailRegisterDTO vo) {
        return null;
    }

//    private User getByUserName(@NotBlank(message = "邮箱不能为空！") String email) {
//    }
//
//    private void checkCode() {
//    }
//
//    private void checkEmail(@NotBlank(message = "邮箱不能为空！") String email) {
//    }
}
