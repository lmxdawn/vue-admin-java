package com.lmxdawn.api.admin.converter;


import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.form.ad.AdSaveForm;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将 广告的表单 转为 广告实体类
 */
public class AdSaveForm2AdConverter {

    /**
     * 将 广告的表单 转为 广告实体类
     * @param adSaveForm 表单
     * @return 如果为 null ，证明参数有误
     */
    public static Ad convert(AdSaveForm adSaveForm) {

        // 判断
        if (null != adSaveForm.getChannelType() &&
                adSaveForm.getChannelType() > 0 &&
                (null == adSaveForm.getChannelList() ||
                        adSaveForm.getChannelList().isEmpty())) {
            return null;
        }
        if (null != adSaveForm.getAndroidVersionType() &&
                adSaveForm.getAndroidVersionType() > 0 &&
                (null == adSaveForm.getAndroidVersionList() ||
                        adSaveForm.getAndroidVersionList().isEmpty())) {
            return null;
        }
        if (null != adSaveForm.getIosVersionType() &&
                adSaveForm.getIosVersionType() > 0 &&
                (null == adSaveForm.getIosVersionList() ||
                        adSaveForm.getIosVersionList().isEmpty())) {
            return null;
        }

        Ad ad = new Ad();
        BeanUtils.copyProperties(adSaveForm, ad);

        if (null != adSaveForm.getChannelList()) {
            List<String> channelList = adSaveForm.getChannelList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            channelList = new ArrayList<>(new HashSet<>(channelList));
            if (!channelList.isEmpty())
                ad.setChannelList(StringUtils.join(channelList, ","));
        }

        if (null != adSaveForm.getAndroidVersionList()) {
            List<String> androidVersionList = adSaveForm.getAndroidVersionList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            androidVersionList = new ArrayList<>(new HashSet<>(androidVersionList));
            if (!androidVersionList.isEmpty())
                ad.setAndroidVersionList(StringUtils.join(androidVersionList, ","));
        }
        if (null != adSaveForm.getIosVersionList()) {
            List<String> iosVersionList = adSaveForm.getIosVersionList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            iosVersionList = new ArrayList<>(new HashSet<>(iosVersionList));
            if (!iosVersionList.isEmpty())
                ad.setIosVersionList(StringUtils.join(iosVersionList, ","));
        }
        return ad;
    }

}
