package com.eula.budu.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author DesLUO
 */
@Data
@ApiModel(value = "登录")
public class LoginDTO {
    @NotBlank(message = "用户名不能为空！")
    private String username;
    @NotBlank(message = "密码不能为空！")
    private String password;

    private Boolean rememberMe;
}
