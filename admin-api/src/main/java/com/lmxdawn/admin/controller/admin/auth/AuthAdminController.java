package com.lmxdawn.admin.controller.admin.auth;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.form.admin.auth.AuthAdminForm;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.service.auth.AuthRoleAdminService;
import com.lmxdawn.admin.service.auth.AuthRoleService;
import com.lmxdawn.admin.utils.ResultVOUtils;
import com.lmxdawn.admin.vo.PageSimpleVO;
import com.lmxdawn.admin.vo.ResultVO;
import com.lmxdawn.admin.vo.admin.auth.AdminAuthAdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理员相关
 */
@RestController
public class AuthAdminController {

    @Resource
    private AuthAdminService authAdminService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    /**
     * 获取管理员列表
     */
    @GetMapping("/admin/auth/admin/index")
    public ResultVO index(@RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                          @RequestParam(value = "status", required = false) Integer status,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "role_id", required = false) Long roleId) {

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("username", username);

        if (roleId != null) {
            List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByRoleId(roleId);
            List<Long> ids = new ArrayList<>();
            if (authRoleAdmins != null && !authRoleAdmins.isEmpty()) {
                ids = authRoleAdmins.stream().map(AuthRoleAdmin::getAdminId).collect(Collectors.toList());
            }
            map.put("ids", ids);
        }

        PageInfo<AuthAdmin> authAdminPageInfo = authAdminService.listAdminPage(page, limit, map);
        List<Long> adminIds = authAdminPageInfo.getList().stream().map(AuthAdmin::getId).collect(Collectors.toList());
        List<AuthRoleAdmin> authRoleAdminList = authRoleAdminService.listByAdminIdIn(adminIds);

        // 视图列表
        List<AdminAuthAdminVo> adminAuthAdminVoList = new ArrayList<>();
        authAdminPageInfo.getList().forEach(v -> {
            AdminAuthAdminVo adminAuthAdminVo = new AdminAuthAdminVo();
            BeanUtils.copyProperties(v, adminAuthAdminVo);
            List<Long> roles = new ArrayList<>();
            authRoleAdminList.forEach(authRoleAdmin -> {
                if (v.getId().equals(authRoleAdmin.getAdminId())) {
                    roles.add(authRoleAdmin.getRoleId());
                }
            });
            adminAuthAdminVo.setRoles(roles);
            adminAuthAdminVoList.add(adminAuthAdminVo);
        });

        PageSimpleVO<AdminAuthAdminVo> authAdminPageSimpleVO = new PageSimpleVO<>();
        authAdminPageSimpleVO.setTotal(authAdminPageInfo.getTotal());
        authAdminPageSimpleVO.setList(adminAuthAdminVoList);

        return ResultVOUtils.success(authAdminPageSimpleVO);

    }



    /**
     * 获取角色列表
     */
    @GetMapping("/admin/auth/admin/roleList")
    public ResultVO roleList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "limit",defaultValue = "50") Integer limit,
                             Map<String, Object> map) {

        return ResultVOUtils.success(authRoleService.listAuthAdminRolePage(page, limit, map));

    }


    /**
     * 新增
     * @return
     */
    @PostMapping("/admin/auth/admin/save")
    public ResultVO save(@RequestBody @Valid AuthAdminForm authAdminForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }


        AuthAdmin authAdmin = authAdminService.insertAuthAdminForm(authAdminForm);

        AdminAuthAdminVo adminAuthAdminVo = new AdminAuthAdminVo();
        BeanUtils.copyProperties(authAdmin, adminAuthAdminVo);
        adminAuthAdminVo.setRoles(authAdminForm.getRoles());

        return ResultVOUtils.success(adminAuthAdminVo);
    }

    /**
     * 修改
     * @return
     */
    @PostMapping("/admin/auth/admin/edit")
    public ResultVO edit(@RequestBody @Valid AuthAdminForm authAdminForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authAdminForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = authAdminService.updateAuthAdminForm(authAdminForm);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "网络错误！");
        }
        return ResultVOUtils.success();
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("/admin/auth/admin/delete")
    public ResultVO delete(@RequestBody AuthAdminForm authAdminForm) {

        if (authAdminForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = authAdminService.deleteById(authAdminForm.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
