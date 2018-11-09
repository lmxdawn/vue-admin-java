package com.lmxdawn.admin.enums;

import lombok.Getter;

/**
 * 返回结果的枚举类
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),
    NOT_NETWORK(1, "系统繁忙，请稍后再试。"),
    LOGIN_VERIFY_FALL(2, "登录失效"),
    PARAM_VERIFY_FALL(3, "参数验证错误"),
    DATA_NOT(4, "没有相关数据"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
