package com.sr.framework.common.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.status.ErrorStatus;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.*;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 捕获日志logback Appender。
 * @author SummerRain
 * @Date 2024/9/12 17:44
 * @Description:
 */
public class CaptureLogAppender extends AppenderBase<ILoggingEvent> {
  private final static Cache<String, List<Log>> cache = CacheBuilder.newBuilder().maximumSize(3).expireAfterWrite(120, TimeUnit.SECONDS).build();

  public static final String MDC_KEY = "CAPTURE-LOG";

  /**
   * 最大返回行数
   */
  public static final int MAX_LINE_NUM = 2000;

  @Getter
  @Setter
  private Layout<ILoggingEvent> layout;

  @Override
  protected void append(ILoggingEvent eventObject) {
    if (!isStarted()) {
      addStatus(new ErrorStatus("Appender [" + name + "] not started.", this));
      return;
    }

    String cacheKey = MDC.get(MDC_KEY);
    if (cacheKey != null) {
      List<Log> logList = getLog(cacheKey);
      logList.add(convertLog(eventObject));
      if (logList.size() > MAX_LINE_NUM) {
        logList.remove(0);
      }
    }
  }

  @SneakyThrows
  public static List<Log> getLog(String key) {
    return cache.get(key, ()->new LinkedList<>());
  }

  private Log convertLog(ILoggingEvent eventObject) {
    return new Log(eventObject.getTimeStamp(), eventObject.getLevel().toString(), layout.doLayout(eventObject));
  }

  @Data
  @AllArgsConstructor
  public static class Log implements Serializable {
    public Log(){

    }
    private long time;
    private String level;
    private String message;
  }
}

