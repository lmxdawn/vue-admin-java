package com.lmxdawn.api.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * 简单的分页返回对象
 */
@Data
public class PageSimpleVO<T> {
    // 总数
    private Long total;
    // 列表
    private List<T> list;
}
