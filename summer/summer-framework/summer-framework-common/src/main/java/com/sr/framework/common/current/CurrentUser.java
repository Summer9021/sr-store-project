package com.sr.framework.common.current;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentUser implements Serializable {
  /**
   * 序列化 ID
   */
  private static final long         serialVersionUID = 1L;
  /**
   * 身份证件
   */
  private              String       id;
  /**
   * ns键
   */
  private              String       nsKey;
  /**
   * 用户名
   */
  private              String       username;
  /**
   * 真实姓名
   */
  private              String       realName;
  /**
   * 当前组织id
   */
  private              String       currentOrgId;
  /**
   * 组织id列表
   */
  private              List<String> orgIdList;
  /**
   * 角色id列表
   */
  private              List<String> roleIdList;
}