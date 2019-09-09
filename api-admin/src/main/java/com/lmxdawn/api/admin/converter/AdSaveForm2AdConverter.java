package com.lmxdawn.api.admin.converter;


import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.req.ad.AdSaveRequest;
import org.apache.commons.lang3.StringUtils;
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
     * @param adSaveRequest 表单
     * @return 如果为 null ，证明参数有误
     */
    public static Ad convert(AdSaveRequest adSaveRequest) {

        // 判断
        if (null != adSaveRequest.getChannelType() &&
                adSaveRequest.getChannelType() > 0 &&
                (null == adSaveRequest.getChannelList() ||
                        adSaveRequest.getChannelList().isEmpty())) {
            return null;
        }
        if (null != adSaveRequest.getAndroidVersionType() &&
                adSaveRequest.getAndroidVersionType() > 0 &&
                (null == adSaveRequest.getAndroidVersionList() ||
                        adSaveRequest.getAndroidVersionList().isEmpty())) {
            return null;
        }
        if (null != adSaveRequest.getIosVersionType() &&
                adSaveRequest.getIosVersionType() > 0 &&
                (null == adSaveRequest.getIosVersionList() ||
                        adSaveRequest.getIosVersionList().isEmpty())) {
            return null;
        }

        Ad ad = new Ad();
        BeanUtils.copyProperties(adSaveRequest, ad);

        if (null != adSaveRequest.getChannelList()) {
            List<String> channelList = adSaveRequest.getChannelList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            channelList = new ArrayList<>(new HashSet<>(channelList));
            if (!channelList.isEmpty())
                ad.setChannelList(StringUtils.join(channelList, ","));
        }

        if (null != adSaveRequest.getAndroidVersionList()) {
            List<String> androidVersionList = adSaveRequest.getAndroidVersionList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            androidVersionList = new ArrayList<>(new HashSet<>(androidVersionList));
            if (!androidVersionList.isEmpty())
                ad.setAndroidVersionList(StringUtils.join(androidVersionList, ","));
        }
        if (null != adSaveRequest.getIosVersionList()) {
            List<String> iosVersionList = adSaveRequest.getIosVersionList().stream()
                    .map(v -> v.replace(",", ""))
                    .collect(Collectors.toList());
            iosVersionList = new ArrayList<>(new HashSet<>(iosVersionList));
            if (!iosVersionList.isEmpty())
                ad.setIosVersionList(StringUtils.join(iosVersionList, ","));
        }
        return ad;
    }

}
