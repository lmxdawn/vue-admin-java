package com.lmxdawn.api.admin.res.ad;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 后台管理的 广告位的 VO
 */
@Data
public class AdSiteResponse {
    private Long siteId;
    private String siteName;
    private String describe;
    private List<AdSimpleResponse> ads;
    private Date modifiedTime;
}
