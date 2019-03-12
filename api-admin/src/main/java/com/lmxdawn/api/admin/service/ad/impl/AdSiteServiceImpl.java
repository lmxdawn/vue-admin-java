package com.lmxdawn.api.admin.service.ad.impl;

import com.github.pagehelper.PageHelper;
import com.lmxdawn.api.admin.dao.ad.AdDao;
import com.lmxdawn.api.admin.dao.ad.AdSiteDao;
import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.admin.req.ad.AdSiteQueryRequest;
import com.lmxdawn.api.admin.service.ad.AdSiteService;
import com.lmxdawn.api.admin.res.ad.AdSimpleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdSiteServiceImpl implements AdSiteService {

    @Resource
    private AdDao adDao;

    @Resource
    private AdSiteDao adSiteDao;

    @Override
    public List<AdSite> listAdminPage(AdSiteQueryRequest adSiteQueryRequest) {
        if (adSiteQueryRequest == null) {
            return Collections.emptyList();
        }
        int offset = (adSiteQueryRequest.getPage() - 1) * adSiteQueryRequest.getLimit();
        PageHelper.offsetPage(offset, adSiteQueryRequest.getLimit());

        return adSiteDao.listAdmin(adSiteQueryRequest);
    }

    @Override
    public List<AdSimpleResponse> listAdminByAdIdsIn(List<Long> adIds) {
        if (adIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<AdSimpleResponse> adSimpleResponseList = adDao.listAdminByAdIdsIn(adIds).stream()
                .map(item -> {
                    AdSimpleResponse adSimpleResponse = new AdSimpleResponse();
                    BeanUtils.copyProperties(item, adSimpleResponse);
                    return adSimpleResponse;
                }).collect(Collectors.toList());

        return adSimpleResponseList;
    }

    @Override
    public boolean insertAdSite(AdSite adSite) {
        return adSiteDao.insertAdSite(adSite);
    }

    @Override
    public boolean updateAdSite(AdSite adSite) {
        return adSiteDao.updateAdSite(adSite);
    }

    @Override
    public boolean deleteBySiteId(Long siteId) {
        return adSiteDao.deleteBySiteId(siteId);
    }
}
