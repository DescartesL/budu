package com.eula.budu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录方式
 * @author DesLUO
 */

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    EMAIL(1, "邮箱登录", "emailLoginStrategyImpl"),

    QQ(2, "QQ登录", "qqLoginStrategyImpl"),

    WEIBO(3, "微博登录", "weiboLoginStrategyImpl"),

    GITEE(4, "码云登录", "gitEELoginStrategyImpl");


    private final Integer type;

    private final String desc;

    private final String strategy;
}
