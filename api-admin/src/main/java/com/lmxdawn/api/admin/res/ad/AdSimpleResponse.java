package com.lmxdawn.api.admin.res.ad;

import lombok.Data;


/**
 * 后台管理的 广告的简单的 VO
 */
@Data
public class AdSimpleResponse {

    private Long adId;
    private String title;
    private String describe;
    private Integer status;

}
