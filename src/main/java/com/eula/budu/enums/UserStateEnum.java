package com.eula.budu.enums;


/**
 * @author DesLUO
 */

public enum UserStateEnum {
    /**
     * 禁用
     */
    disable(0),
    /**
     * 正常
     */
    normal(1);

    public int code;

    UserStateEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
