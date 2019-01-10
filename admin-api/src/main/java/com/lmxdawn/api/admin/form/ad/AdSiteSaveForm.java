package com.lmxdawn.api.admin.form.ad;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 广告的保存表单
 */
@Data
public class AdSiteSaveForm {

    private Long siteId;
    @NotEmpty(message = "请输入广告位名称")
    private String siteName;
    private String describe;
    private List<Long> adIds;
    private Date createTime;
    private Date modifiedTime;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 1, message = "参数错误")
    private Integer status;

}
