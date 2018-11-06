package com.lmxdawn.admin.service;


import com.lmxdawn.admin.entity.AuthAdmin;

import java.util.List;

public interface AuthAdminService {

    List<AuthAdmin> findByPage(String username, Integer currPage, Integer pageSize);
    
}
