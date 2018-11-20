package com.lmxdawn.admin.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmxdawn.admin.dao.auth.AuthAdminDao;
import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.exception.JsonException;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.service.auth.AuthRoleAdminService;
import com.lmxdawn.admin.vo.PageSimpleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {
    
    @Resource
    private AuthAdminDao authAdminDao;

    @Resource
    private AuthRoleAdminService authRoleAdminService;
    
    @Override
    public PageSimpleVO<AuthAdmin> listAdminPage(Integer page, Integer limit, Map<String, Object> map) {
        if (map != null
                && map.containsKey("role_id")
                && map.get("role_id") != null) {
            List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByRoleId((Long) map.get("role_id"));
            List<Long> ids = new ArrayList<>();
            if (authRoleAdmins != null && !authRoleAdmins.isEmpty()) {
                ids = authRoleAdmins.stream().map(AuthRoleAdmin::getAdminId).collect(Collectors.toList());
            }
            map.put("ids", ids);
        }
        page = page != null && page > 0 ? page : 1;
        limit = limit != null && limit > 0 && limit < 20 ? limit : 20;
        int offset = (page - 1) * limit;
        PageHelper.offsetPage(offset, limit);
        List<AuthAdmin> list = authAdminDao.listAdminPage(map);
        PageInfo<AuthAdmin> pageInfo = new PageInfo<>(list);
        PageSimpleVO<AuthAdmin> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        pageSimpleVO.setList(list);
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

    /**
     * 新增
     * @param authAdmin
     * @return
     */
    @Override
    public boolean insertAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            if (byUserName != null) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }

        return authAdminDao.insertAuthAdmin(authAdmin);
    }

    @Override
    public boolean updateAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getId() == null) {
            return false;
        }

        // 当用户名不为空时，检查是否存在
        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            // 判断是否存在，剔除自己
            if (byUserName.getId() != null && !byUserName.getId().equals(authAdmin.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }

        return authAdminDao.updateAuthAdmin(authAdmin);
    }


}
