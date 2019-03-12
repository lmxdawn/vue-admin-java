package com.lmxdawn.api.admin.service.ad;


import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.req.ad.AdQueryRequest;

import java.util.List;

public interface AdService {

    List<Ad> listAdminPage(AdQueryRequest adQueryRequest);

    List<Ad> listAdminByAdIdsIn(List<Long> adIds);

    boolean insertAd(Ad ad);

    boolean updateAd(Ad ad);

    boolean deleteByAdId(Long id);

}
