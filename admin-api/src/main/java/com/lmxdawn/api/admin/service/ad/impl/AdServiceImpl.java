package com.lmxdawn.api.admin.service.ad.impl;

import com.github.pagehelper.PageHelper;
import com.lmxdawn.api.admin.dao.ad.AdDao;
import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.form.ad.AdQueryForm;
import com.lmxdawn.api.admin.service.ad.AdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Resource
    private AdDao adDao;

    @Override
    public List<Ad> listAdminPage(AdQueryForm adQueryForm) {
        if (adQueryForm == null) {
            return Collections.emptyList();
        }
        int offset = (adQueryForm.getPage() - 1) * adQueryForm.getLimit();
        PageHelper.offsetPage(offset, adQueryForm.getLimit());
        return adDao.listAdmin(adQueryForm);
    }

    @Override
    public List<Ad> listAdminByAdIdsIn(List<Long> adIds) {
        return adDao.listAdminByAdIdsIn(adIds);
    }

    @Override
    public boolean insertAd(Ad ad) {
        return adDao.insertAd(ad);
    }

    @Override
    public boolean updateAd(Ad ad) {
        return adDao.updateAd(ad);
    }

    @Override
    public boolean deleteByAdId(Long adId) {
        return adDao.deleteByAdId(adId);
    }
}
