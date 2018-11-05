package com.lmxdawn.admin.service.impl;

import com.lmxdawn.admin.dao.AuthAdminDao;
import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.AuthAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {
    
    @Autowired
    private AuthAdminDao authAdminDao;
    
    @Override
    public List<AuthAdmin> queryList() {
        return authAdminDao.queryAuthAdmin();
    }
}
