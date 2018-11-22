package com.lmxdawn.api.admin.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.dao.auth.AuthRoleDao;
import com.lmxdawn.api.admin.entity.auth.AuthRole;
import com.lmxdawn.api.admin.service.auth.AuthRoleService;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.auth.AuthAdminRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param map
     * @return
     */
    @Override
    public PageSimpleVO<AuthRole> listAdminPage(Integer page, Integer limit, Map<String, Object> map) {
        page = page != null && page > 0 ? page : 1;
        limit = limit != null && limit > 0 && limit < 20 ? limit : 20;
        int offset = (page - 1) * limit;
        PageHelper.offsetPage(offset, limit);
        List<AuthRole> list = authRoleDao.listAdminPage(map);
        PageInfo<AuthRole> pageInfo = new PageInfo<>(list);
        PageSimpleVO<AuthRole> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        pageSimpleVO.setList(list);
        return pageSimpleVO;
    }

    /**
     * 查询管理员页面的列表
     * @param page
     * @param limit
     * @param map
     * @return
     */
    @Override
    public PageSimpleVO<AuthAdminRoleVO> listAuthAdminRolePage(Integer page, Integer limit, Map<String, Object> map) {
        page = page != null && page > 0 ? page : 1;
        limit = limit != null && limit > 0 && limit < 20 ? limit : 20;
        int offset = (page - 1) * limit;
        PageHelper.offsetPage(offset, limit);
        List<AuthRole> list = authRoleDao.listAuthAdminRolePage(map);
        PageInfo<AuthRole> pageInfo = new PageInfo<>(list);
        PageSimpleVO<AuthAdminRoleVO> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        List<AuthAdminRoleVO> authAdminRoleVOS = new ArrayList<>();
        if (!list.isEmpty()) {
            authAdminRoleVOS = list.stream().map(e -> {
                AuthAdminRoleVO authAdminRoleVO = new AuthAdminRoleVO();
                BeanUtils.copyProperties(e, authAdminRoleVO);
                return authAdminRoleVO;
            }).collect(Collectors.toList());
        }
        pageSimpleVO.setList(authAdminRoleVOS);
        return pageSimpleVO;
    }
}
