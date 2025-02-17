package com.sr.framework.common.feign;

import com.sr.framework.common.exception.BizException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * feign收到异常时的解码处理
 *
 * @author SummerRain
 * @Date 2024/9/12 15:17
 * @Description:
 */
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String s, Response response) {

    String msg = null;
    try {
      msg = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    // 抛出自定义的业务异常s
    return new BizException(msg);

  }
}

