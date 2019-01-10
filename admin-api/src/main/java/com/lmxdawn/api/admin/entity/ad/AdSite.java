package com.lmxdawn.api.admin.entity.ad;

import lombok.Data;

import java.util.Date;

/**
 * 广告位实体类
 */
@Data
public class AdSite {

  private Long siteId;
  private String siteName;
  private String describe;
  private String adIds;
  private Date createTime;
  private Date modifiedTime;

}
