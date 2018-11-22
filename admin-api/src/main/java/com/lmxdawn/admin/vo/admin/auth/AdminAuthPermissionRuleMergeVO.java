package com.lmxdawn.admin.vo.admin.auth;

import lombok.Data;

import java.util.List;

/**
 * 权限列表整合为多维数组的视图
 */
@Data
public class AdminAuthPermissionRuleMergeVO {

    private Long id;
    private Long pid;
    private String name;
    private String title;
    private Long status;
    private String condition;
    private Long listorder;

    private List<AdminAuthPermissionRuleMergeVO> children;

}
