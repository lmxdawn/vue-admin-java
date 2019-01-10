package com.lmxdawn.api.admin.vo.ad;

import lombok.Data;


/**
 * 后台管理的 广告的简单的 VO
 */
@Data
public class AdSimpleVo {

    private Long adId;
    private String title;
    private String describe;
    private Integer status;

}
