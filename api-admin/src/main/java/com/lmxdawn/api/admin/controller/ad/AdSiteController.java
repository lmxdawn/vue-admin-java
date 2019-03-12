package com.lmxdawn.api.admin.controller.ad;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.common.enums.ResultEnum;
import com.lmxdawn.api.admin.req.ad.AdSiteQueryRequest;
import com.lmxdawn.api.admin.req.ad.AdSiteSaveRequest;
import com.lmxdawn.api.admin.service.ad.AdSiteService;
import com.lmxdawn.api.admin.res.PageSimpleResponse;
import com.lmxdawn.api.common.res.BaseResponse;
import com.lmxdawn.api.admin.res.ad.AdSimpleResponse;
import com.lmxdawn.api.admin.res.ad.AdSiteResponse;
import com.lmxdawn.api.common.converter.LongList2StringConverter;
import com.lmxdawn.api.common.converter.String2LongListConverter;
import com.lmxdawn.api.common.util.ResultVOUtils;
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
    public BaseResponse index(@Valid AdSiteQueryRequest adSiteQueryRequest,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<AdSite> adSiteList = adSiteService.listAdminPage(adSiteQueryRequest);

        Set<Long> adIds = new HashSet<>();
        for (AdSite item: adSiteList) {
            List<Long> ads = String2LongListConverter.convert(item.getAdIds(), ",");
            adIds.addAll(ads);
        }

        // 查询根据所有广告id查询广告列表
        List<AdSimpleResponse> adList = adSiteService.listAdminByAdIdsIn(new ArrayList<>(adIds));

        List<AdSiteResponse> adSiteResponseList = adSiteList.stream().map(item -> {
            AdSiteResponse adSiteResponse = new AdSiteResponse();
            List<Long> ads = String2LongListConverter.convert(item.getAdIds(), ",");
            List<AdSimpleResponse> adSimpleResponseList = new ArrayList<>();
            if (!ads.isEmpty()) {
                for (AdSimpleResponse adSimpleVo: adList) {
                    if (ads.contains(adSimpleVo.getAdId())) {
                        adSimpleResponseList.add(adSimpleVo);
                    }
                }
            }
            adSiteResponse.setAds(adSimpleResponseList);
            BeanUtils.copyProperties(item, adSiteResponse);
            return adSiteResponse;
        }).collect(Collectors.toList());

        PageInfo<AdSite> pageInfo = new PageInfo<>(adSiteList);
        PageSimpleResponse<AdSiteResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(pageInfo.getTotal());
        pageSimpleResponse.setList(adSiteResponseList);
        return ResultVOUtils.success(pageSimpleResponse);

    }

    /**
     * 获取广告列表
     */
    @AuthRuleAnnotation("admin/ad/site/adList")
    @PostMapping("/admin/ad/site/adList")
    public BaseResponse adList(@RequestBody Long[] adIds) {

        List<Long> adIdList = new ArrayList<>();
        if (adIds.length > 0) {
            adIdList = Arrays.asList(adIds);
        }

        List<AdSimpleResponse> adSimpleResponseList = adSiteService.listAdminByAdIdsIn(adIdList);

        PageSimpleResponse<AdSimpleResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(1L);
        pageSimpleResponse.setList(adSimpleResponseList);
        return ResultVOUtils.success(pageSimpleResponse);

    }


    /**
     * 新增
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/site/save")
    @PostMapping("/admin/ad/site/save")
    public BaseResponse save(@RequestBody @Valid AdSiteSaveRequest adSiteSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AdSite adSite = new AdSite();
        BeanUtils.copyProperties(adSiteSaveRequest, adSite);
        adSite.setAdIds(LongList2StringConverter.convert(adSiteSaveRequest.getAdIds(), ","));
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
    public BaseResponse edit(@RequestBody @Valid AdSiteSaveRequest adSiteSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AdSite adSite = new AdSite();
        BeanUtils.copyProperties(adSiteSaveRequest, adSite);
        adSite.setAdIds(LongList2StringConverter.convert(adSiteSaveRequest.getAdIds(), ","));
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
    public BaseResponse delete(@RequestBody AdSiteSaveRequest adSiteSaveRequest) {

        if (ObjectUtils.isEmpty(adSiteSaveRequest.getSiteId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = adSiteService.deleteBySiteId(adSiteSaveRequest.getSiteId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success();
    }


}
