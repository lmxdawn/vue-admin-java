package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.admin.dao.auth.AuthPermissionRuleDao;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class AuthPermissionRuleServiceImpl implements AuthPermissionRuleService {

    @Resource
    private AuthPermissionRuleDao authPermissionRuleDao;

    /**
     * 根据多个id查询
     * @param ids
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByIdIn(List<Long> ids) {
        return authPermissionRuleDao.listByIdIn(ids);
    }

    /**
     * 根据父级 pid 查询
     * @param pid
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByPid(Long pid) {
        return authPermissionRuleDao.listByPid(pid);
    }

    /**
     * 查询所有
     * @param map
     * @return
     */
    @Override
    public List<AuthPermissionRule> listAll(Map<String, Object> map) {
        return authPermissionRuleDao.listAll(map);
    }
}
