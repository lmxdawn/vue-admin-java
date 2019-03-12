package com.lmxdawn.api.admin.controller.ad;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.converter.AdSaveForm2AdConverter;
import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.common.enums.ResultEnum;
import com.lmxdawn.api.admin.req.ad.AdQueryRequest;
import com.lmxdawn.api.admin.req.ad.AdSaveRequest;
import com.lmxdawn.api.admin.service.ad.AdService;
import com.lmxdawn.api.admin.res.PageSimpleResponse;
import com.lmxdawn.api.common.res.BaseResponse;
import com.lmxdawn.api.admin.res.ad.AdResponse;
import com.lmxdawn.api.common.converter.String2StringListConverter;
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
public class AdController {

    @Resource
    private AdService adService;

    /**
     * 获取列表
     */
    @AuthRuleAnnotation("admin/ad/ad/index")
    @GetMapping("/admin/ad/ad/index")
    public BaseResponse index(@Valid AdQueryRequest adQueryRequest,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<Ad> adList = adService.listAdminPage(adQueryRequest);

        List<AdResponse> adResponseList = adList.stream().map(item -> {
            AdResponse adResponse = new AdResponse();
            BeanUtils.copyProperties(item, adResponse);
            List<String> channelList = String2StringListConverter.convert(item.getChannelList(), ",");
            adResponse.setChannelList(channelList);
            List<String> androidVersionList = String2StringListConverter.convert(item.getAndroidVersionList(), ",");
            adResponse.setAndroidVersionList(androidVersionList);
            List<String> iosVersionList = String2StringListConverter.convert(item.getIosVersionList(), ",");
            adResponse.setIosVersionList(iosVersionList);
            return adResponse;
        }).collect(Collectors.toList());

        PageInfo<Ad> pageInfo = new PageInfo<>(adList);
        PageSimpleResponse<AdResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(pageInfo.getTotal());
        pageSimpleResponse.setList(adResponseList);
        return ResultVOUtils.success(pageSimpleResponse);

    }


    /**
     * 新增
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/ad/save")
    @PostMapping("/admin/ad/ad/save")
    public BaseResponse save(@RequestBody @Valid AdSaveRequest adSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        Ad ad = AdSaveForm2AdConverter.convert(adSaveRequest);

        if (null == ad) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误");
        }

        Date nowDate = new Date();
        ad.setCreateTime(nowDate);
        ad.setModifiedTime(nowDate);

        boolean b = adService.insertAd(ad);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        Map<String, Long> res = new HashMap<>();
        res.put("adId", ad.getAdId());
        return ResultVOUtils.success(res);
    }

    /**
     * 修改
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/ad/edit")
    @PostMapping("/admin/ad/ad/edit")
    public BaseResponse edit(@RequestBody @Valid AdSaveRequest adSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (ObjectUtils.isEmpty(adSaveRequest.getAdId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        Ad ad = AdSaveForm2AdConverter.convert(adSaveRequest);

        if (null == ad) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误");
        }

        Date nowDate = new Date();
        ad.setModifiedTime(nowDate);

        boolean b = adService.updateAd(ad);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/ad/delete")
    @PostMapping("/admin/ad/ad/delete")
    public BaseResponse delete(@RequestBody AdSaveRequest adSaveRequest) {

        if (ObjectUtils.isEmpty(adSaveRequest.getAdId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = adService.deleteByAdId(adSaveRequest.getAdId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success();
    }


}
