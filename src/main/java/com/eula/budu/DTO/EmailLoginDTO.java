package com.eula.budu.DTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "邮箱登录信息")
public class EmailLoginDTO {
    @NotBlank(message = "邮箱不能为空！")
    @ApiModelProperty(name = "email", value = "email", required = true, dataType = "String")
    private String email;
    @NotBlank(message = "密码不能为空!")
    @ApiModelProperty(name = "password", value = "password", required = true, dataType = "String")
    private String password;
}
