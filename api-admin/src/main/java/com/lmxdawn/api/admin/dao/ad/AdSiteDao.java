package com.lmxdawn.api.admin.dao.ad;

import com.lmxdawn.api.admin.entity.ad.AdSite;
import com.lmxdawn.api.admin.req.ad.AdSiteQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdSiteDao {

    /**
     * 后台业务查询列表
     * @return 列表
     */
    List<AdSite> listAdmin(AdSiteQueryRequest adSiteQueryRequest);

    /**
     * 插入
     * @param adSite
     * @return
     */
    boolean insertAdSite(AdSite adSite);

    /**
     * 更新
     * @param adSite
     * @return
     */
    boolean updateAdSite(AdSite adSite);

    /**
     * 删除
     * @param siteId
     * @return
     */
    boolean deleteBySiteId(Long siteId);

}
