package com.lmxdawn.admin.controller.admin.auth;

import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.form.admin.auth.AuthAdminForm;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.service.auth.AuthRoleService;
import com.lmxdawn.admin.utils.ResultVOUtils;
import com.lmxdawn.admin.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员相关
 */
@RestController
public class AuthAdminController {

    @Resource
    private AuthAdminService authAdminService;

    @Resource
    private AuthRoleService authRoleService;

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
        map.put("role_id", roleId);

        return ResultVOUtils.success(authAdminService.listAdminPage(page, limit, map));

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

        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminForm, authAdmin);

        authAdminService.insertAuthAdmin(authAdmin);
        return ResultVOUtils.success(authAdmin);
    }


}
