package com.lmxdawn.admin.service.admin;


import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.vo.PageSimpleVO;

public interface AuthAdminService {

    PageSimpleVO<AuthAdmin> findByPage(String username, Integer currPage, Integer pageSize);

    AuthAdmin findByUserName(String userName);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    AuthAdmin findById(Long id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    AuthAdmin findPwdById(Long id);

    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean updateAuthAdmin(AuthAdmin authAdmin);

}
