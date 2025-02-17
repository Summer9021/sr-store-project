package com.sr.framework.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**  Request Header - 用户信息等上下文传递
 * @author SummerRain
 * @Date 2024/9/12 15:17
 * @Description:
 */
public class FeignRequestInterceptor implements RequestInterceptor {

  public final static String PREFIX = "SUMMER-";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes != null) {
      HttpServletRequest request = attributes.getRequest();
      Enumeration<String> hs = request.getHeaderNames();
      while (hs.hasMoreElements()) {
        String h = hs.nextElement();
        if (h.toUpperCase().startsWith(PREFIX)) {
          requestTemplate.header(h, request.getHeader(h));
        }
      }
    }
  }
}
