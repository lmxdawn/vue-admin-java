package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.AuthRoleAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthRoleAdminDao {

    /**
     * 根据 adminId 查询
     * @param adminId 传入的 adminId
     * @return
     */
    List<AuthRoleAdmin> selectByAdminId(Long adminId);

}
