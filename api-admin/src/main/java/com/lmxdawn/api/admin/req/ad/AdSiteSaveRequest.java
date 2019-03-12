package com.lmxdawn.api.admin.req.ad;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 广告的保存表单
 */
@Data
public class AdSiteSaveRequest {

    private Long siteId;
    @NotEmpty(message = "请输入广告位名称")
    private String siteName;
    private String describe;
    private List<Long> adIds;
    private Date createTime;
    private Date modifiedTime;

}
