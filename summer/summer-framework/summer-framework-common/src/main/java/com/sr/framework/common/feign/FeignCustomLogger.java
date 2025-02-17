package com.sr.framework.common.feign;

import feign.Response;
import feign.Util;
import feign.slf4j.Slf4jLogger;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author SummerRain
 * @Date 2024/9/12 15:16
 * @Description:
 */
public class FeignCustomLogger extends Slf4jLogger {

  private final Logger logger;

  public FeignCustomLogger() {
    this(FeignCustomLogger.class);
  }

  public FeignCustomLogger(Class<?> clazz) {
    this(LoggerFactory.getLogger(clazz));
  }

  public FeignCustomLogger(String name) {
    this(LoggerFactory.getLogger(name));
  }

  FeignCustomLogger(Logger logger) {
    this.logger = logger;
  }

  @Override
  protected Response logAndRebufferResponse(String configKey,
                                            Level logLevel,
                                            Response response,
                                            long elapsedTime)
      throws IOException {
    LoggerContext context = new LoggerContext();
    context.setUrl(response.request().url());
    context.setCost(elapsedTime);
    if (StringUtils.hasText(response.reason())) {
      context.setException(response.reason());
    }
    context.setMethod(response.request().httpMethod().name());
    byte[] requestData = response.request().body();
    context.setRequest(Util.decodeOrDefault(requestData, Util.UTF_8, ""));

    int status = response.status();
    if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
      int bodyLength = 0;
      if (response.body() != null && !(status == 204 || status == 205)) {
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        bodyLength = bodyData.length;
        if (bodyLength > 0) {
          context.setResponse(Util.decodeOrDefault(bodyData, Util.UTF_8, ""));
        }
        context.setBodyLength(bodyLength);
        logger.debug(context.toString());
        return response.toBuilder().body(bodyData).build();
      } else {
        context.setBodyLength(bodyLength);
      }

    }
    logger.debug(context.toString());
    return response;
  }

  /**
   * 日志结构化
   *
   * @author SummerRain
   * @date 2020/2/13 12:34 下午
   * @since 1.0.0
   */
  @Data
  class LoggerContext {
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String method;
    /**
     * 入参
     */
    private String request;
    /**
     * 出参
     */
    private String response;
    /**
     * 耗时
     */
    private Long cost;
    /**
     * 异常
     */
    private String exception;
    /**
     * 响应字节长度
     */
    private  Integer   bodyLength;
    /**
     * 本次请求URL，不是栈源头url
     */
    private String     url;
  }

}
