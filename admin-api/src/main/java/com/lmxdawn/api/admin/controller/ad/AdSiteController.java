package com.lmxdawn.api.admin.controller.ad;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.form.ad.AdSiteQueryForm;
import com.lmxdawn.api.admin.form.ad.AdSiteSaveForm;
import com.lmxdawn.api.admin.service.ad.AdSiteService;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.ResultVO;
import com.lmxdawn.api.admin.vo.ad.AdSimpleVo;
import com.lmxdawn.api.admin.vo.ad.AdSiteVo;
import com.lmxdawn.api.common.converter.String2LongListConverter;
import com.lmxdawn.api.common.utils.ResultVOUtils;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理员相关
 */
@RestController
public class AdSiteController {

    @Resource
    private AdSiteService adSiteService;

    /**
     * 获取管理员列表
     */
    @AuthRuleAnnotation("admin/ad/site/index")
    @GetMapping("/admin/ad/site/index")
    public ResultVO index(@Valid AdSiteQueryForm adSiteQueryForm,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<AdSite> adSiteList = adSiteService.listAdminPage(adSiteQueryForm);

        Set<Long> adIds = new HashSet<>();
        for (AdSite item: adSiteList) {
            List<Long> ads = String2LongListConverter.convert(item.getAdIds(), ",");
            adIds.addAll(ads);
        }

        // 查询根据所有广告id查询广告列表
        List<AdSimpleVo> adList = adSiteService.listAdminByAdIdsIn(new ArrayList<>(adIds));

        List<AdSiteVo> adSiteVoList = adSiteList.stream().map(item -> {
            AdSiteVo adSiteVo = new AdSiteVo();
            List<Long> ads = String2LongListConverter.convert(item.getAdIds(), ",");
            List<AdSimpleVo> adSimpleVoList = new ArrayList<>();
            if (!ads.isEmpty()) {
                for (AdSimpleVo adSimpleVo: adList) {
                    if (ads.contains(adSimpleVo.getAdId())) {
                        adSimpleVoList.add(adSimpleVo);
                    }
                }
            }
            adSiteVo.setAds(adSimpleVoList);
            BeanUtils.copyProperties(item, adSiteVo);
            return adSiteVo;
        }).collect(Collectors.toList());

        PageInfo<AdSite> pageInfo = new PageInfo<>(adSiteList);
        PageSimpleVO<AdSiteVo> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        pageSimpleVO.setList(adSiteVoList);
        return ResultVOUtils.success(pageSimpleVO);

    }

    /**
     * 获取广告列表
     */
    @AuthRuleAnnotation("admin/ad/site/adList")
    @GetMapping("/admin/ad/site/adList")
    public ResultVO adList(@RequestParam(value = "adIds", defaultValue = "") List<Long> adIds) {

        List<AdSimpleVo> adSimpleVoList = adSiteService.listAdminByAdIdsIn(adIds);

        PageSimpleVO<AdSimpleVo> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(1L);
        pageSimpleVO.setList(adSimpleVoList);
        return ResultVOUtils.success(pageSimpleVO);

    }


    /**
     * 新增
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/site/save")
    @PostMapping("/admin/ad/site/save")
    public ResultVO save(@RequestBody @Valid AdSiteSaveForm adSiteSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AdSite adSite = new AdSite();
        BeanUtils.copyProperties(adSiteSaveForm, adSite);

        if (null != adSiteSaveForm.getAdIds()) {
            adSite.setAdIds(StringUtils.join(adSiteSaveForm.getAdIds(), ","));
        }

        Date nowDate = new Date();
        adSite.setCreateTime(nowDate);
        adSite.setModifiedTime(nowDate);

        boolean b = adSiteService.insertAdSite(adSite);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        Map<String, Long> res = new HashMap<>();
        res.put("siteId", adSite.getSiteId());
        return ResultVOUtils.success(res);
    }

    /**
     * 修改
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/site/edit")
    @PostMapping("/admin/ad/site/edit")
    public ResultVO edit(@RequestBody @Valid AdSiteSaveForm adSiteSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AdSite adSite = new AdSite();
        BeanUtils.copyProperties(adSiteSaveForm, adSite);

        if (null != adSiteSaveForm.getAdIds()) {
            adSite.setAdIds(StringUtils.join(adSiteSaveForm.getAdIds(), ","));
        }

        Date nowDate = new Date();
        adSite.setModifiedTime(nowDate);

        boolean b = adSiteService.updateAdSite(adSite);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success();
    }

    /**
     * 删除 // TODO 这个接口讲道理的话，是不应该存在的
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/site/delete")
    @PostMapping("/admin/ad/site/delete")
    public ResultVO delete(@RequestBody AdSiteSaveForm adSiteSaveForm) {

        if (ObjectUtils.isEmpty(adSiteSaveForm.getSiteId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = adSiteService.deleteBySiteId(adSiteSaveForm.getSiteId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success();
    }


}
