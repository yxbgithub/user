package com.yxb.server.enums;

import lombok.Getter;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家");

    private Integer code;
    private String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
