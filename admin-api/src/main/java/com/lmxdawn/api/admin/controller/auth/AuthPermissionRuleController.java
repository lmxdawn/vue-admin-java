package com.lmxdawn.api.admin.controller.auth;

import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.form.auth.AuthPermissionRuleSaveForm;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.api.admin.utils.PermissionRuleTreeUtils;
import com.lmxdawn.api.admin.vo.ResultVO;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleMergeVO;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleVO;
import com.lmxdawn.api.common.utils.ResultVOUtils;
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
    public ResultVO index() {


        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();
        List<AuthPermissionRuleMergeVO> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList,0L);

        Map<String,Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        return ResultVOUtils.success(restMap);
    }

    /**
     * 新增
     * @param authPermissionRuleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/save")
    @PostMapping("/admin/auth/permission_rule/save")
    public ResultVO save(@RequestBody @Valid AuthPermissionRuleSaveForm authPermissionRuleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveForm.getPid() == null) {
            authPermissionRuleSaveForm.setPid(0L); // 默认设置
        }
        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveForm, authPermissionRule);

        boolean b = authPermissionRuleService.insertAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        AuthPermissionRuleVO authPermissionRuleVO = new AuthPermissionRuleVO();
        BeanUtils.copyProperties(authPermissionRule, authPermissionRuleVO);

        return ResultVOUtils.success(authPermissionRuleVO);
    }

    /**
     * 编辑
     * @param authPermissionRuleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/edit")
    @PostMapping("/admin/auth/permission_rule/edit")
    public ResultVO edit(@RequestBody @Valid AuthPermissionRuleSaveForm authPermissionRuleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        authPermissionRuleSaveForm.setPid(null); // 不能修改父级 pid

        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveForm, authPermissionRule);

        boolean b = authPermissionRuleService.updateAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     * @param authPermissionRuleSaveForm
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/delete")
    @PostMapping("/admin/auth/permission_rule/delete")
    public ResultVO delete(@RequestBody AuthPermissionRuleSaveForm authPermissionRuleSaveForm) {

        if (authPermissionRuleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authPermissionRuleService.deleteById(authPermissionRuleSaveForm.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
