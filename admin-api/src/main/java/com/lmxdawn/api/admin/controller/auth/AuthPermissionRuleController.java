package com.lmxdawn.api.admin.controller.auth;

import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.api.admin.utils.PermissionRuleTreeUtils;
import com.lmxdawn.api.admin.vo.ResultVO;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleMergeVO;
import com.lmxdawn.api.common.utils.ResultVOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限规则相关
 */
@RestController
public class AuthPermissionRuleController {

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @AuthRuleAnnotation("/admin/auth/permission_rule/index")
    @GetMapping("/admin/auth/permission_rule/index")
    public ResultVO index(@RequestParam(value = "status", required = false) Integer status,
                          @RequestParam(value = "name", required = false) String name,
                          Map<String,Object> map) {

        map.put("status", status);
        map.put("name", name);

        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll(map);
        List<AuthPermissionRuleMergeVO> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList,0L);

        Map<String,Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        return ResultVOUtils.success(restMap);
    }

    @AuthRuleAnnotation("/admin/auth/permission_rule/save")
    @PostMapping("/admin/auth/permission_rule/save")
    public ResultVO save(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        Map<String,Object> restMap = new HashMap<>();
        restMap.put("id", 1);
        restMap.put("pid", 1);
        restMap.put("name", "user_manage");
        restMap.put("title", "用户管理");
        restMap.put("status", 1);
        return ResultVOUtils.success(restMap);
    }


}
