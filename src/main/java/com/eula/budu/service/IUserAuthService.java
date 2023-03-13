package com.eula.budu.service;

import com.eula.budu.DTO.EmailLoginDTO;
import com.eula.budu.DTO.EmailRegisterDTO;
import com.eula.budu.DTO.LoginDTO;
import com.eula.budu.common.ResponseResult;
import com.eula.budu.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
public interface IUserAuthService extends IService<UserAuth> {

    ResponseResult emailLogin(EmailLoginDTO emailLoginDTO);

    RequestBody login(@Valid LoginDTO loginDTO);

    ResponseResult emailRegister(@Valid EmailRegisterDTO emailRegisterDTO);

    ResponseResult updatePassword(EmailRegisterDTO emailRegisterDTO);

    ResponseResult sendEmailCode(String email);
}
