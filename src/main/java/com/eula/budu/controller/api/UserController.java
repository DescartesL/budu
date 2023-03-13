package com.eula.budu.controller.api;

import com.eula.budu.DTO.EmailLoginDTO;
import com.eula.budu.DTO.EmailRegisterDTO;
import com.eula.budu.DTO.LoginDTO;
import com.eula.budu.common.ResponseResult;
import com.eula.budu.service.IUserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
@Api(tags = "登录")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    IUserAuthService userAuthService;

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @ApiOperation(value = "普通登录", httpMethod = "POST", response = ResponseResult.class, notes = "普通登录")
    public RequestBody login(@Valid @RequestBody LoginDTO loginDTO){
        return userAuthService.login(loginDTO);
    }

    @RequestMapping(value = "/emailLogin", method = RequestMethod.POST)
    @ApiOperation(value = "邮箱登录", httpMethod = "POST", response = ResponseResult.class, notes = "邮箱登录")
    public ResponseResult emailLogin(@Valid @RequestBody EmailLoginDTO emailLoginDTO){
        return userAuthService.emailLogin(emailLoginDTO);
    }

    @RequestMapping(value = "/emailRegister", method = RequestMethod.POST)
    @ApiOperation(value = "邮箱注册", httpMethod = "POST", response = ResponseResult.class, notes = "邮箱注册" )
    public ResponseResult emailRegister(@Valid @RequestBody EmailRegisterDTO emailRegisterDTO){
        return userAuthService.emailRegister(emailRegisterDTO);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", httpMethod = "POST", response = ResponseResult.class, notes = "修改密码")
    public ResponseResult updatePassword(@Valid @RequestBody EmailRegisterDTO emailRegisterDTO){
        return userAuthService.updatePassword(emailRegisterDTO);
    }

    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.GET)
    @ApiOperation(value = "请求验证码", httpMethod = "GET", response = ResponseResult.class, notes = "请求验证码")
    public ResponseResult sendEmailCode(String email){
        return userAuthService.sendEmailCode(email);
    }
}
