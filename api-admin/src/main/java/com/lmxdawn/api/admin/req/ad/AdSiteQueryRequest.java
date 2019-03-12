package com.lmxdawn.api.admin.req.ad;

import com.lmxdawn.api.admin.req.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdSiteQueryRequest extends ListPageRequest {

    private Long siteId;

}
