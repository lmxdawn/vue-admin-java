package com.lmxdawn.api.admin.service.ad.impl;

import com.github.pagehelper.PageHelper;
import com.lmxdawn.api.admin.dao.ad.AdDao;
import com.lmxdawn.api.admin.dao.ad.AdSiteDao;
import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.admin.form.ad.AdSiteQueryForm;
import com.lmxdawn.api.admin.service.ad.AdSiteService;
import com.lmxdawn.api.admin.vo.ad.AdSimpleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdSiteServiceImpl implements AdSiteService {

    @Resource
    private AdDao adDao;

    @Resource
    private AdSiteDao adSiteDao;

    @Override
    public List<AdSite> listAdminPage(AdSiteQueryForm adSiteQueryForm) {
        if (adSiteQueryForm == null) {
            return Collections.emptyList();
        }
        int offset = (adSiteQueryForm.getPage() - 1) * adSiteQueryForm.getLimit();
        PageHelper.offsetPage(offset, adSiteQueryForm.getLimit());

        return adSiteDao.listAdmin(adSiteQueryForm);
    }

    @Override
    public List<AdSimpleVo> listAdminByAdIdsIn(List<Long> adIds) {
        if (adIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<AdSimpleVo> adSimpleVoList = adDao.listAdminByAdIdsIn(adIds).stream()
                .map(item -> {
                    AdSimpleVo adSimpleVo = new AdSimpleVo();
                    BeanUtils.copyProperties(item, adSimpleVo);
                    return adSimpleVo;
                }).collect(Collectors.toList());

        return adSimpleVoList;
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
