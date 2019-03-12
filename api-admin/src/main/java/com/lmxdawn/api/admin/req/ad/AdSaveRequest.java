package com.lmxdawn.api.admin.req.ad;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 广告的保存表单
 */
@Data
public class AdSaveRequest {

    private Long adId;
    @NotEmpty(message = "请输入广告名称")
    private String title;
    private String describe;
    @NotEmpty(message = "请选择广告的封面图片")
    private String pic;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 2, message = "参数错误")
    private Integer jumpType;
    private String jumpUrl;
    private String iosUrl;
    private String androidUrl;
    private String wxaAppid;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 2, message = "参数错误")
    private Integer channelType;
    private List<String> channelList;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 2, message = "参数错误")
    private Integer androidVersionType;
    private List<String> androidVersionList;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 2, message = "参数错误")
    private Integer iosVersionType;
    private List<String> iosVersionList;
    private Integer newShowStartNum;
    private Integer newShowMaxNum;
    private Integer oldShowStartNum;
    private Integer oldShowMaxNum;
    private Date startTime;
    private Date endTime;
    private String eventName;
    @NotNull(message = "请选择状态")
    @Min(value = 0, message = "参数错误")
    @Max(value = 1, message = "参数错误")
    private Integer status;

}
