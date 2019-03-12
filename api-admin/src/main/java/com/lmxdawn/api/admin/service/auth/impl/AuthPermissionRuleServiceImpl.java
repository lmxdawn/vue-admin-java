package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.admin.dao.auth.AuthPermissionRuleDao;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.common.enums.ResultEnum;
import com.lmxdawn.api.admin.exception.JsonException;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class AuthPermissionRuleServiceImpl implements AuthPermissionRuleService {

    @Resource
    private AuthPermissionRuleDao authPermissionRuleDao;

    /**
     * 根据多个id查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByIdIn(List<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return authPermissionRuleDao.listByIdIn(ids);
    }

    /**
     * 根据父级 pid 查询
     *
     * @param pid
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByPid(Long pid) {
        return authPermissionRuleDao.listByPid(pid);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<AuthPermissionRule> listAll() {
        return authPermissionRuleDao.listAll();
    }

    /**
     * 插入
     * @param authPermissionRule
     * @return
     */
    @Override
    public boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule) {

        // 查询是否存在
        AuthPermissionRule byName = authPermissionRuleDao.findByName(authPermissionRule.getName());
        if (byName != null) {
            throw new JsonException(ResultEnum.DATA_REPEAT, "当前权限规则已存在");
        }

        authPermissionRule.setCreateTime(new Date());
        authPermissionRule.setUpdateTime(new Date());
        if (authPermissionRule.getListorder() == null) {
            authPermissionRule.setListorder(999);
        }
        return authPermissionRuleDao.insertAuthPermissionRule(authPermissionRule);
    }

    /**
     * 更新
     * @param authPermissionRule
     * @return
     */
    @Override
    public boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule) {

        if (authPermissionRule.getName() != null) {
            // 查询是否存在
            AuthPermissionRule byName = authPermissionRuleDao.findByName(authPermissionRule.getName());
            if (byName != null && !authPermissionRule.getId().equals(byName.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前权限规则已存在");
            }
        }

        authPermissionRule.setUpdateTime(new Date());
        return authPermissionRuleDao.updateAuthPermissionRule(authPermissionRule);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authPermissionRuleDao.deleteById(id);
    }
}
