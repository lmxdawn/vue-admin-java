package com.lmxdawn.api.admin.req.auth;

import com.lmxdawn.api.admin.req.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色的查询表单
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthRoleQueryRequest extends ListPageRequest {
    private String name;
    private Integer status;

}
