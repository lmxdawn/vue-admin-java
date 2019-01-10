package com.lmxdawn.api.admin.vo.ad;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 后台管理的 广告位的 VO
 */
@Data
public class AdSiteVo {
    private Long siteId;
    private String siteName;
    private String describe;
    private List<AdSimpleVo> ads;
    private Date modifiedTime;
}
