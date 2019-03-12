package com.lmxdawn.api.admin.controller.auth;

import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.common.enums.ResultEnum;
import com.lmxdawn.api.admin.req.auth.AuthPermissionRuleSaveRequest;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.api.admin.util.PermissionRuleTreeUtils;
import com.lmxdawn.api.common.res.BaseResponse;
import com.lmxdawn.api.admin.res.auth.AuthPermissionRuleMergeResponse;
import com.lmxdawn.api.common.util.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    /**
     * 列表
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/index")
    @GetMapping("/admin/auth/permission_rule/index")
    public BaseResponse index() {


        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();
        List<AuthPermissionRuleMergeResponse> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList,0L);

        Map<String,Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        return ResultVOUtils.success(restMap);
    }

    /**
     * 新增
     * @param authPermissionRuleSaveRequest
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/save")
    @PostMapping("/admin/auth/permission_rule/save")
    public BaseResponse save(@RequestBody @Valid AuthPermissionRuleSaveRequest authPermissionRuleSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveRequest.getPid() == null) {
            authPermissionRuleSaveRequest.setPid(0L); // 默认设置
        }
        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveRequest, authPermissionRule);

        boolean b = authPermissionRuleService.insertAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        Map<String, Long> res = new HashMap<>();
        res.put("id", authPermissionRule.getId());
        return ResultVOUtils.success(res);
    }

    /**
     * 编辑
     * @param authPermissionRuleSaveRequest
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/edit")
    @PostMapping("/admin/auth/permission_rule/edit")
    public BaseResponse edit(@RequestBody @Valid AuthPermissionRuleSaveRequest authPermissionRuleSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        authPermissionRuleSaveRequest.setPid(null); // 不能修改父级 pid

        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveRequest, authPermissionRule);

        boolean b = authPermissionRuleService.updateAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     * @param authPermissionRuleSaveRequest
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/delete")
    @PostMapping("/admin/auth/permission_rule/delete")
    public BaseResponse delete(@RequestBody AuthPermissionRuleSaveRequest authPermissionRuleSaveRequest) {

        if (authPermissionRuleSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authPermissionRuleService.deleteById(authPermissionRuleSaveRequest.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
