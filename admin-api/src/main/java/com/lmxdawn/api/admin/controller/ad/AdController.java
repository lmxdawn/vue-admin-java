package com.lmxdawn.api.admin.controller.ad;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.converter.AdSaveForm2AdConverter;
import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.form.ad.AdQueryForm;
import com.lmxdawn.api.admin.form.ad.AdSaveForm;
import com.lmxdawn.api.admin.service.ad.AdService;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.ResultVO;
import com.lmxdawn.api.admin.vo.ad.AdVo;
import com.lmxdawn.api.common.converter.String2StringListConverter;
import com.lmxdawn.api.common.utils.ResultVOUtils;
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
    public ResultVO index(@Valid AdQueryForm adQueryForm,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<Ad> adList = adService.listAdminPage(adQueryForm);

        System.out.println(String2StringListConverter.convert("ss", ","));

        List<AdVo> adVoList = adList.stream().map(item -> {
            AdVo adVo = new AdVo();
            BeanUtils.copyProperties(item, adVo);
            List<String> channelList = String2StringListConverter.convert(item.getChannelList(), ",");
            adVo.setChannelList(channelList);
            List<String> androidVersionList = String2StringListConverter.convert(item.getAndroidVersionList(), ",");
            adVo.setAndroidVersionList(androidVersionList);
            List<String> iosVersionList = String2StringListConverter.convert(item.getIosVersionList(), ",");
            adVo.setIosVersionList(iosVersionList);
            return adVo;
        }).collect(Collectors.toList());

        PageInfo<Ad> pageInfo = new PageInfo<>(adList);
        PageSimpleVO<AdVo> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        pageSimpleVO.setList(adVoList);
        return ResultVOUtils.success(pageSimpleVO);

    }


    /**
     * 新增
     *
     * @return
     */
    @AuthRuleAnnotation("admin/ad/ad/save")
    @PostMapping("/admin/ad/ad/save")
    public ResultVO save(@RequestBody @Valid AdSaveForm adSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        Ad ad = AdSaveForm2AdConverter.convert(adSaveForm);

        if (null == ad) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误");
        }

        Date nowDate = new Date();
        ad.setCreateTime(nowDate);
        ad.setModifiedTime(nowDate);

        System.out.println(ad);
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
    public ResultVO edit(@RequestBody @Valid AdSaveForm adSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (ObjectUtils.isEmpty(adSaveForm.getAdId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        Ad ad = AdSaveForm2AdConverter.convert(adSaveForm);

        if (null == ad) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误");
        }

        Date nowDate = new Date();
        ad.setModifiedTime(nowDate);

        System.out.println(ad);
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
    public ResultVO delete(@RequestBody AdSaveForm adSaveForm) {

        if (ObjectUtils.isEmpty(adSaveForm.getAdId())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = adService.deleteByAdId(adSaveForm.getAdId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success();
    }


}
