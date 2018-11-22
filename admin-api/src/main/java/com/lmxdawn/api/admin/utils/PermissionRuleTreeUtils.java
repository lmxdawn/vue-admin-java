package com.lmxdawn.api.admin.utils;

import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleMergeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限规则生成树形节点工具类
 */
public class PermissionRuleTreeUtils {

    /**
     * 多维数组
     */
    public static List<AuthPermissionRuleMergeVO> merge(List<AuthPermissionRule> authPermissionRuleList,
                                                        Long pid) {

        List<AuthPermissionRuleMergeVO> authPermissionRuleMergeVOList = new ArrayList<>();
        for (AuthPermissionRule v : authPermissionRuleList) {
            AuthPermissionRuleMergeVO authPermissionRuleMergeVO = new AuthPermissionRuleMergeVO();
            BeanUtils.copyProperties(v, authPermissionRuleMergeVO);
            if (pid.equals(v.getPid())) {
                authPermissionRuleMergeVO.setChildren(merge(authPermissionRuleList, v.getId()));
                authPermissionRuleMergeVOList.add(authPermissionRuleMergeVO);
            }
        }

        return authPermissionRuleMergeVOList;
    }



}
