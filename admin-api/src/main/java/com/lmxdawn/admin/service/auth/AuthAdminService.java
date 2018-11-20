package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.vo.PageSimpleVO;

import java.util.Map;

public interface AuthAdminService {

    PageSimpleVO<AuthAdmin> listAdminPage(Integer page, Integer limit, Map<String, Object> map);

    AuthAdmin findByUserName(String userName);


    AuthAdmin findById(Long id);


    AuthAdmin findPwdById(Long id);

    boolean insertAuthAdmin(AuthAdmin authAdmin);

    boolean updateAuthAdmin(AuthAdmin authAdmin);

}
