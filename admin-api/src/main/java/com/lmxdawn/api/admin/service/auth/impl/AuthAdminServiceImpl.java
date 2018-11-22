package com.lmxdawn.api.admin.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.dao.auth.AuthAdminDao;
import com.lmxdawn.api.admin.entity.auth.AuthAdmin;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.exception.JsonException;
import com.lmxdawn.api.admin.form.admin.auth.AuthAdminForm;
import com.lmxdawn.api.admin.service.auth.AuthAdminService;
import com.lmxdawn.api.admin.service.auth.AuthRoleAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {
    
    @Resource
    private AuthAdminDao authAdminDao;

    @Resource
    private AuthRoleAdminService authRoleAdminService;
    
    @Override
    public PageInfo<AuthAdmin> listAdminPage(Integer page, Integer limit, Map<String, Object> map) {
        page = page != null && page > 0 ? page : 1;
        limit = limit != null && limit > 0 && limit < 20 ? limit : 20;
        int offset = (page - 1) * limit;
        PageHelper.offsetPage(offset, limit);
        List<AuthAdmin> list = authAdminDao.listAdminPage(map);
        return new PageInfo<>(list);
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
        authAdmin.setCreateTime(new Date());
        return authAdminDao.insertAuthAdmin(authAdmin);
    }

    @Override
    public AuthAdmin insertAuthAdminForm(AuthAdminForm authAdminForm) {

        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminForm, authAdmin);

        boolean b = insertAuthAdmin(authAdmin);
        if (!b) {
            return null;
        }
        boolean isRoles = authAdminForm.getRoles() != null && !authAdminForm.getRoles().isEmpty();
        if (b && isRoles) {
            authRoleAdminService.insertRolesAdminIdAll(authAdminForm.getRoles(), authAdmin.getId());
        }

        return authAdmin;
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
            if (byUserName != null && !authAdmin.getId().equals(byUserName.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }

        return authAdminDao.updateAuthAdmin(authAdmin);
    }

    @Override
    public boolean updateAuthAdminForm(AuthAdminForm authAdminForm) {
        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminForm, authAdmin);
        boolean b = updateAuthAdmin(authAdmin);
        boolean isRoles = authAdminForm.getRoles() != null && !authAdminForm.getRoles().isEmpty();
        // 先删除
        authRoleAdminService.deleteByAdminId(authAdmin.getId());
        if (b && isRoles) {
            authRoleAdminService.insertRolesAdminIdAll(authAdminForm.getRoles(), authAdmin.getId());
        }

        return b;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authAdminDao.deleteById(id);
    }


}
