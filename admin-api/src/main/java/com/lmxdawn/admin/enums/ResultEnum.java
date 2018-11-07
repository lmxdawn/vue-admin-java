package com.lmxdawn.admin.enums;

import lombok.Getter;

/**
 * 返回结果的枚举类
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
