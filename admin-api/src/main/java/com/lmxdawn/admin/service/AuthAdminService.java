package com.lmxdawn.admin.service;


import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.vo.PageSimpleVO;

public interface AuthAdminService {

    PageSimpleVO<AuthAdmin> findByPage(String username, Integer currPage, Integer pageSize);
    
}
