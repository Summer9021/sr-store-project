package com.sr.framework.common.feign;

import feign.Logger;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  Feign通用配置
 * @author SummerRain
 * @Date 2024/9/12 15:15
 * @Description:
 */
@Configuration
@Slf4j
public class FeignConfiguration {

  @Value("${feign.client.config.default.loggerLevel:BASIC}")
  private Logger.Level level;

  @Bean
  Logger.Level feignLoggerLevel() {
    log.info("CommonFeignConfiguration.level:{}", this.level);
    return level;
  }

  @Bean
  Logger feignCustomLogger() {
    return new FeignCustomLogger();
  }

  @Bean
  public ErrorDecoder feignError() {
    return new FeignErrorDecoder();
  }

  @Bean
  public FeignRequestInterceptor feignRequestInterceptor(){
    return new FeignRequestInterceptor();
  }

}

