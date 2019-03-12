package com.lmxdawn.api.admin.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页的表单
 */
@Data
public class ListPageRequest {

    @NotNull(message = "请选择第几页")
    @Min(message = "分页参数错误", value = 1)
    private Integer page;

    @NotNull(message = "请填写每页查询数量")
    @Min(value = 1, message = "分页参数不能小于1")
    @Max(value = 50, message = "分页参数不能大于50")
    private Integer limit;

}
