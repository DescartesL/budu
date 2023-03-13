package com.eula.budu.common;

/**
 * @author DesLUO
 */

public enum ResultConstants {
    // 成功
    SUCCESS(200, "SUCCESS"),
    // 失败
    FAILURE(400, "FAILURE"),

    // 系统级别错误
    ERROR(-1, "操作异常"),
    ERROR_DEFAULT(500, "系统繁忙，请稍后重试"),
    NOT_LOGIN(401, "请先登录"),
    NO_PERMISSION(-2, "无权限"),
    ERROR_PASSEORD(-8, "账户或者密码错误"),
    DISABLE_ACCOUNT(-9, "账号已禁用"),

    ERROR_MUST_REGISTER(10017, "请先注册"),

    EMAIL_ERROR(-10, "邮箱格式不对"),
    ERROR_EXCEPTION_CODE(10003, "验证码不正确或者已经过期！"),
    EMAIL_IS_EXIST(-11,"账号已经存在，请直接登录！"),
    PASSWORD_ILLEGAL(-13,"密码格式不合法!"),
    ERROR_PASSWORD(-8,"用户帐号或者密码错误!"),
    EMAIL_DISABLE_LOGIN(-12,"该邮箱账号已被管理员禁止登录!");


    public int code;
    public String desc;

    ResultConstants(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
