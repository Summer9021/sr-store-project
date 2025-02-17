package com.sr.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:11
 * @Description:
 */
@Component
@Slf4j
public class SpringBeanUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * 根据bean的名字获取bean
   *
   * @param beanName
   * @return
   */
  public static Object getBean(String beanName) {
    return getApplicationContext().getBean(beanName);
  }

  /**
   * 根据类型获取bean
   *
   * @param tClass
   * @param <T>
   * @return
   */
  public static <T> T getBean(Class<T> tClass) {
    return getApplicationContext().getBean(tClass);
  }

  public static <T> T  getBean(String beanName, Class<T> tClass) {
    return getApplicationContext().getBean(beanName, tClass);
  }

  public static ApplicationContext getApplicationContext() {
    Assert.notNull(applicationContext, "SpringBeanUtil 没有注册为Spring bean，请使用 @Import 导入 SpringBeanUtil。");
    return applicationContext;
  }

  public static Optional<ApplicationContext> getContext() {
    return Optional.ofNullable(applicationContext);
  }

}
