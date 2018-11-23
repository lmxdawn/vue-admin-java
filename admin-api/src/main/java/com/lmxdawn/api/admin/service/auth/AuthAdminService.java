package com.lmxdawn.api.admin.service.auth;


import com.lmxdawn.api.admin.entity.auth.AuthAdmin;
import com.lmxdawn.api.admin.form.auth.AuthAdminQueryForm;

import java.util.List;

public interface AuthAdminService {

    List<AuthAdmin> listAdminPage(AuthAdminQueryForm authAdminQueryForm);

    AuthAdmin findByUserName(String userName);


    AuthAdmin findById(Long id);


    AuthAdmin findPwdById(Long id);

    boolean insertAuthAdmin(AuthAdmin authAdmin);

    boolean updateAuthAdmin(AuthAdmin authAdmin);

    boolean deleteById(Long id);

}
