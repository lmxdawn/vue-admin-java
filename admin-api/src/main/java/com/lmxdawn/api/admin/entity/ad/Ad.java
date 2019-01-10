package com.lmxdawn.api.admin.entity.ad;

import lombok.Data;

import java.util.Date;

/**
 * 广告实体类
 */
@Data
public class Ad {

  private Long adId;
  private String title;
  private String describe;
  private String pic;
  private Integer jumpType;
  private String jumpUrl;
  private String iosUrl;
  private String androidUrl;
  private String wxaAppid;
  private Integer channelType;
  private String channelList;
  private Integer androidVersionType;
  private String androidVersionList;
  private Integer iosVersionType;
  private String iosVersionList;
  private Integer newShowStartNum;
  private Integer newShowMaxNum;
  private Integer oldShowStartNum;
  private Integer oldShowMaxNum;
  private Date startTime;
  private Date endTime;
  private String eventName;
  private Integer status;
  private Date createTime;
  private Date modifiedTime;

}
