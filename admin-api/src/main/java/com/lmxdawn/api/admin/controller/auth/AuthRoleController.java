package com.lmxdawn.api.admin.controller.auth;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.entity.auth.AuthPermission;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.entity.auth.AuthRole;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.form.auth.AuthPermissionRuleSaveForm;
import com.lmxdawn.api.admin.form.auth.AuthRoleAuthForm;
import com.lmxdawn.api.admin.form.auth.AuthRoleQueryForm;
import com.lmxdawn.api.admin.form.auth.AuthRoleSaveForm;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.api.admin.service.auth.AuthPermissionService;
import com.lmxdawn.api.admin.service.auth.AuthRoleService;
import com.lmxdawn.api.admin.utils.PermissionRuleTreeUtils;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.ResultVO;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleMergeVO;
import com.lmxdawn.api.admin.vo.auth.AuthRoleVO;
import com.lmxdawn.api.common.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色相关
 */
@RestController
public class AuthRoleController {

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @Resource
    private AuthPermissionService authPermissionService;

    /**
     * 角色列表
     */
    @AuthRuleAnnotation("/admin/auth/role/index")
    @GetMapping("/admin/auth/role/index")
    public ResultVO index(@Valid AuthRoleQueryForm authRoleQueryForm,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<AuthRole> authRoleList = authRoleService.listAdminPage(authRoleQueryForm);
        List<AuthRoleVO> authRoleVOList = authRoleList.stream().map(item -> {
            AuthRoleVO authRoleVO = new AuthRoleVO();
            BeanUtils.copyProperties(item, authRoleVO);
            return authRoleVO;
        }).collect(Collectors.toList());

        PageInfo<AuthRole> pageInfo = new PageInfo<>(authRoleList);
        PageSimpleVO<AuthRoleVO> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotal(pageInfo.getTotal());
        pageSimpleVO.setList(authRoleVOList);
        return ResultVOUtils.success(pageSimpleVO);
    }

    /**
     * 获取授权列表
     *
     * @param id
     * @return
     */
    @AuthRuleAnnotation("/admin/auth/role/authList")
    @GetMapping("/admin/auth/role/authList")
    public ResultVO authList(@RequestParam("id") Long id) {

        // 查询当前角色拥有的权限id
        List<AuthPermission> authPermissionList = authPermissionService.listByRoleId(id);
        List<Long> checkedKeys = authPermissionList.stream()
                .map(AuthPermission::getPermissionRuleId)
                .collect(Collectors.toList());

        // 查询所有权限规则
        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();
        List<AuthPermissionRuleMergeVO> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList, 0L);

        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        restMap.put("checkedKeys", checkedKeys);
        return ResultVOUtils.success(restMap);
    }

    @AuthRuleAnnotation("/admin/auth/role/auth")
    @PostMapping("/admin/auth/role/auth")
    public ResultVO auth(@RequestBody @Valid AuthRoleAuthForm authRoleAuthForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 先删除之前的授权
        authPermissionService.deleteByRoleId(authRoleAuthForm.getRoleId());

        List<AuthPermission> authPermissionList = authRoleAuthForm.getAuthRules().stream()
                .map(aLong -> {
                    AuthPermission authPermission = new AuthPermission();
                    authPermission.setRoleId(authRoleAuthForm.getRoleId());
                    authPermission.setPermissionRuleId(aLong);
                    authPermission.setType("admin");
                    return authPermission;
                }).collect(Collectors.toList());

        int i = authPermissionService.insertAuthPermissionAll(authPermissionList);

        return ResultVOUtils.success();
    }

    /**
     * 新增
     *
     * @param authRoleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("/admin/auth/role/save")
    @PostMapping("/admin/auth/role/save")
    public ResultVO save(@RequestBody @Valid AuthRoleSaveForm authRoleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AuthRole byName = authRoleService.findByName(authRoleSaveForm.getName());
        if (byName != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveForm, authRole);

        boolean b = authRoleService.insertAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        AuthRoleVO authRoleVO = new AuthRoleVO();
        BeanUtils.copyProperties(authRole, authRoleVO);

        return ResultVOUtils.success(authRoleVO);
    }

    /**
     * 编辑
     *
     * @param authRoleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("/admin/auth/role/edit")
    @PostMapping("/admin/auth/role/edit")
    public ResultVO edit(@RequestBody @Valid AuthRoleSaveForm authRoleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authRoleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        // 检查是否存在当前角色
        AuthRole byName = authRoleService.findByName(authRoleSaveForm.getName());
        if (byName != null && !authRoleSaveForm.getId().equals(byName.getId())) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveForm, authRole);

        boolean b = authRoleService.updateAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     *
     * @param authRoleSaveForm
     * @return
     */
    @AuthRuleAnnotation("/admin/auth/role/delete")
    @PostMapping("/admin/auth/role/delete")
    public ResultVO delete(@RequestBody AuthRoleSaveForm authRoleSaveForm) {

        if (authRoleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authRoleService.deleteById(authRoleSaveForm.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
