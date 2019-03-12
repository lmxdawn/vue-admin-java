package com.lmxdawn.api.admin.dao.ad;

import com.lmxdawn.api.admin.entity.ad.Ad;
import com.lmxdawn.api.admin.req.ad.AdQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdDao {

    /**
     * 后台业务查询列表
     * @return 列表
     */
    List<Ad> listAdmin(AdQueryRequest adQueryRequest);

    /**
     * 根据adIds 查询
     * @return 列表
     */
    List<Ad> listAdminByAdIdsIn(List<Long> adIds);

    /**
     * 插入
     * @param ad
     * @return
     */
    boolean insertAd(Ad ad);

    /**
     * 更新
     * @param ad
     * @return
     */
    boolean updateAd(Ad ad);

    /**
     * 删除
     * @param adId
     * @return
     */
    boolean deleteByAdId(Long adId);

}
