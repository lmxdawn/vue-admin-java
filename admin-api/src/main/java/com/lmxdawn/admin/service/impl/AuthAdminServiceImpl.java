package com.lmxdawn.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmxdawn.admin.dao.AuthAdminDao;
import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.AuthAdminService;
import com.lmxdawn.admin.vo.PageSimpleVO;
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
    public PageSimpleVO<AuthAdmin> findByPage(String username, Integer currPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        PageHelper.offsetPage(currPage, pageSize);
        List<AuthAdmin> list = authAdminDao.findByPage(map);
        PageInfo<AuthAdmin> page = new PageInfo<>(list);
        PageSimpleVO<AuthAdmin> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(page.getTotal());
        pageSimpleVO.setList(page.getList());
        return pageSimpleVO;
    }

    @Override
    public AuthAdmin findByUserName(String userName) {
        return authAdminDao.findByUserName(userName);
    }

}
