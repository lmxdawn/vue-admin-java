package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.dao.auth.AuthPermissionRuleDao;
import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.admin.service.auth.AuthPermissionRuleService;
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
     * 查询所有
     * @param map
     * @return
     */
    @Override
    public List<AuthPermissionRule> listAll(Map<String, Object> map) {
        return authPermissionRuleDao.listAll(map);
    }
}
