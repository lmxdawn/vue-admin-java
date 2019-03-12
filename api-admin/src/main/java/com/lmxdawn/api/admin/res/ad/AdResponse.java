package com.lmxdawn.api.admin.res.ad;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 后台管理的 广告的 VO
 */
@Data
public class AdResponse {

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
    private List<String> channelList;
    private Integer androidVersionType;
    private List<String> androidVersionList;
    private Integer iosVersionType;
    private List<String> iosVersionList;
    private Integer newShowStartNum;
    private Integer newShowMaxNum;
    private Integer oldShowStartNum;
    private Integer oldShowMaxNum;
    private Date startTime;
    private Date endTime;
    private String eventName;
    private Integer status;

}
