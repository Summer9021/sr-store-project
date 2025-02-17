package com.sr.framework.common.current;

import com.google.common.collect.Lists;
import com.sr.framework.common.constant.GatewayConstant;
import com.sr.framework.common.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

/**
 * 当前助手
 *
 * @author SummerRain
 * @date 2024/01/10
 */
@Slf4j
public final class CurrentHelper {

  /**
   * 逗号_
   */
  public static final String COMMA                       = ",";

  /**
   * 获取当前用户
   *
   * @return {@link CurrentUser}
   */
  public static CurrentUser getCurrentUser() {
    try {
      return Optional.of((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .map(ServletRequestAttributes::getRequest).map(request -> {
            // 如果 header 不存在 检查开发环境 mock 是否开启 开启则返回 否则返回 null
            if (Objects.isNull(request.getHeader(GatewayConstant.HEADER_USER_ID_KEY)) && checkDevEnv()) {
              log.info("本地开发环境mock 用户信息逻辑 ...");
              return CurrentUser.builder()
                  .id(GatewayConstant.DEV_MOCK_USER_ID)
                  .nsKey(GatewayConstant.DEV_MOCK_NS_KEY)
                  .currentOrgId(GatewayConstant.DEV_MOCK_CURRENT_ORG_ID)
                  .username(GatewayConstant.DEV_MOCK_USER_NAME)
                  .realName(GatewayConstant.DEV_MOCK_REAL_NAME)
                  .roleIdList(Lists.newArrayList())
                  .orgIdList(Lists.newArrayList()).build();
            }
            return CurrentUser.builder().id(request.getHeader(GatewayConstant.HEADER_USER_ID_KEY))
                .nsKey(request.getHeader(GatewayConstant.HEADER_NS_KEY_KEY))
                .currentOrgId(request.getHeader(GatewayConstant.HEADER_ORG_ID_KEY))
                .username(Optional.ofNullable(request.getHeader(GatewayConstant.HEADER_USER_NAME_KEY))
                    .map(val -> new String(Base64.getDecoder().decode(val), StandardCharsets.UTF_8))
                    .orElse(null))
                .realName(Optional.ofNullable(request.getHeader(GatewayConstant.HEADER_REAL_NAME_KEY))
                    .map(val -> new String(Base64.getDecoder().decode(val), StandardCharsets.UTF_8))
                    .orElse(null))
                .roleIdList(Optional.ofNullable(request.getHeader(GatewayConstant.HEADER_ROLE_IDS_KEY))
                    .map(val -> Lists.newArrayList(StringUtils.split(val, COMMA)))
                    .orElse(Lists.newArrayList()))
                .orgIdList(Optional.ofNullable(request.getHeader(GatewayConstant.HEADER_ORG_IDS_KEY))
                    .map(val -> Lists.newArrayList(StringUtils.split(val, COMMA)))
                    .orElse(Lists.newArrayList())).build();
          }).orElse(null);
    }
    catch (Exception e) {
      log.warn("get current user fail, message:{}", e.getMessage());
      return null;
    }
  }

  /**
   * 检查开发环境是否开启
   *
   * @return boolean
   */
  private static boolean checkDevEnv() {
    return Boolean.TRUE.equals(SpringBeanUtil.getApplicationContext().getEnvironment()
        .getProperty("dev-tool.user-info-enabled", Boolean.class));
  }

  /**
   * 获取当前用户id
   *
   * @return {@link String}
   */
  public static String getCurrentUserId() {
    try {
      return Optional.of((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .map(ServletRequestAttributes::getRequest).map(req -> req.getHeader(GatewayConstant.HEADER_USER_ID_KEY))
          // 如果 header 不存在 检查开发环境 mock 是否开启 开启则返回 否则返回 null
          .orElseGet(() -> checkDevEnv() ? GatewayConstant.DEV_MOCK_USER_ID : null);
    }
    catch (Exception e) {
      log.warn("get current user id fail, message:{}", e.getMessage());
      return null;
    }
  }


  /**
   * @return java.lang.String
   * @author SummerRain
   * @Description 获取当前用户登录的部门id
   * @Date 15:57 2025/1/17
   * @Param []
   **/

  public static String getCurrentOrgId() {
    try {
      return Optional.of((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .map(ServletRequestAttributes::getRequest).map(req -> req.getHeader(GatewayConstant.HEADER_ORG_ID_KEY))
          // 如果 header 不存在 检查开发环境 mock 是否开启 开启则返回 否则返回 null
          .orElseGet(() -> checkDevEnv() ? GatewayConstant.DEV_MOCK_CURRENT_ORG_ID : null);
    }
    catch (Exception e) {
      log.warn("get current org id fail, message:{}", e.getMessage());
      return null;
    }
  }

  /**
   * 获取当前租户id
   *
   * @return {@link String}
   */
  public static String getCurrentTenantId() {
    try {
      return Optional.of((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .map(ServletRequestAttributes::getRequest).map(req -> req.getHeader(GatewayConstant.HEADER_NS_KEY_KEY))
          // 如果 header 不存在 检查开发环境 mock 是否开启 开启则返回 否则返回 null
          .orElseGet(() -> checkDevEnv() ? GatewayConstant.DEV_MOCK_NS_KEY : null);
    }
    catch (Exception e) {
      log.warn("get current tenantId fail, message:{}", e.getMessage());
      return null;
    }
  }
}
