package com.sr.framework.common.jackson;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:20
 * @Description:
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 支持时间戳和自定义格式的LocalDateTime返回序列化
 * @author SummerRain
 *
 */
public class LocalDateTimeDeserializer
    extends com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public LocalDateTimeDeserializer(DateTimeFormatter dtf) {
    super(dtf);
  }

  protected LocalDateTimeDeserializer(
      com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer base, Boolean leniency) {
    super(base, leniency);
  }


  @Override
  protected com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer withDateFormat(DateTimeFormatter dtf) {
    return new LocalDateTimeDeserializer(dtf);
  }

  @Override
  protected com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer withLeniency(Boolean leniency) {
    return new LocalDateTimeDeserializer(this, leniency);
  }

  @Override
  public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                      IOException {
    if (parser.hasToken(JsonToken.VALUE_STRING) && StringUtils.isNumeric(parser.getText().trim())) {
      return fromString(parser, context, parser.getText());
    }
    return super.deserialize(parser, context);
  }

  private LocalDateTime fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
    String string = string0.trim();
    int count = 13 - string.length();
    if (count > 0) {
      StringBuilder sb = new StringBuilder(13);
      sb.append(string);
      for (int i = 0; i < count; i++) {
        sb.append("0");
      }
      string = sb.toString();
    }
    return Instant.ofEpochMilli(Long.parseLong(string)).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
  }


}

