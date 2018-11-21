package com.lmxdawn.admin.service.auth;


import com.github.pagehelper.PageInfo;
import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.form.admin.auth.AuthAdminForm;

import java.util.Map;

public interface AuthAdminService {

    PageInfo<AuthAdmin> listAdminPage(Integer page, Integer limit, Map<String, Object> map);

    AuthAdmin findByUserName(String userName);


    AuthAdmin findById(Long id);


    AuthAdmin findPwdById(Long id);

    boolean insertAuthAdmin(AuthAdmin authAdmin);

    AuthAdmin insertAuthAdminForm(AuthAdminForm authAdminForm);

    boolean updateAuthAdmin(AuthAdmin authAdmin);

    boolean updateAuthAdminForm(AuthAdminForm authAdminForm);

    boolean deleteById(Long id);

}
