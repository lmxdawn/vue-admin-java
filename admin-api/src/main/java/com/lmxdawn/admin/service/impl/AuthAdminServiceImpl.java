package com.lmxdawn.admin.service.impl;

import com.lmxdawn.admin.dao.AuthAdminDao;
import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.AuthAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {
    
    @Resource
    private AuthAdminDao authAdminDao;
    
    @Override
    public List<AuthAdmin> findByPage(String username, Integer currPage, Integer pageSize) {
        currPage = currPage > 0 ? currPage : 1;
        pageSize = pageSize > 0 && pageSize <= 20 ? pageSize : 20;
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("currIndex", (currPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        return authAdminDao.findByPage(map);
    }
    
}
