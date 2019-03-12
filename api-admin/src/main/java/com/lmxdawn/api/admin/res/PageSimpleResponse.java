package com.lmxdawn.api.admin.res;

import lombok.Data;

import java.util.List;

/**
 * 简单的分页返回对象
 */
@Data
public class PageSimpleResponse<T> {
    // 总数
    private Long total;
    // 列表
    private List<T> list;
}
