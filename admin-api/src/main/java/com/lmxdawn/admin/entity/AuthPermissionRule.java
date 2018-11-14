package com.lmxdawn.admin.entity;

import lombok.Data;

/**
 * 规则表
 */
@Data
public class AuthPermissionRule {

  private Long id;
  private Long pid;
  private String name;
  private String title;
  private Long status;
  private String condition;
  private Long listorder;
  private Long createTime;
  private Long updateTime;
}
