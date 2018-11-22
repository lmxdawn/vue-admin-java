package com.lmxdawn.admin.controller.admin.auth;

import com.lmxdawn.admin.annotation.AdminAuthRuleAnnotation;
import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.admin.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 权限规则相关
 */
public class AuthPermissionRuleController {

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @AdminAuthRuleAnnotation("/admin/auth/permission_rule/index")
    @PostMapping("/admin/auth/permission_rule/index")
    public ResultVO index(@RequestParam(value = "status", required = false) Integer status,
                          @RequestParam(value = "name", required = false) String name,
                          Map<String,Object> map) {
        map.put("status", status);
        map.put("name", name);

        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll(map);


        return null;
    }


}
