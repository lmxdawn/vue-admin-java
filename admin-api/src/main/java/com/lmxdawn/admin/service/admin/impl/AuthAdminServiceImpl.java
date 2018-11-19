package com.lmxdawn.admin.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmxdawn.admin.dao.auth.AuthAdminDao;
import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.admin.AuthAdminService;
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

    /**
     * 根据id 获取需要的info
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findById(Long id) {
        return authAdminDao.findById(id);
    }

    /**
     * 根据 id 获取密码字段
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findPwdById(Long id) {
        return authAdminDao.findPwdById(id);
    }

    @Override
    public boolean updateAuthAdmin(AuthAdmin authAdmin) {
        return authAdminDao.updateAuthAdmin(authAdmin);
    }


}
