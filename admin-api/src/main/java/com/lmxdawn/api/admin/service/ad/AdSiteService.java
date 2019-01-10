package com.lmxdawn.api.admin.service.ad;


import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.admin.form.ad.AdSiteQueryForm;
import com.lmxdawn.api.admin.vo.ad.AdSimpleVo;

import java.util.List;

public interface AdSiteService {

    List<AdSite> listAdminPage(AdSiteQueryForm adSiteQueryForm);

    List<AdSimpleVo> listAdminByAdIdsIn(List<Long> adIds);

    boolean insertAdSite(AdSite adSite);

    boolean updateAdSite(AdSite adSite);

    boolean deleteBySiteId(Long siteId);

}
