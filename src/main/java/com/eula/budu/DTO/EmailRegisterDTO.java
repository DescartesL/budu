package com.eula.budu.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author DesLUO
 */
@Data
@ApiModel(value = "邮箱注册")
public class EmailRegisterDTO {
    @NotBlank(message = "邮箱不能为空！")
    @ApiModelProperty(name = "email",value = "email", required = true, dataType = "String")
    private String email;

    @NotBlank(message = "密码不能为空！")
    @ApiModelProperty(name = "password", value = "password", required = true, dataType = "String")
    private String password;

    private String nickName;

    private String code;

}
