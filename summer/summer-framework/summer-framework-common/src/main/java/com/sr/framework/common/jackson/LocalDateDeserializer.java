package com.sr.framework.common.jackson;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:17
 * @Description:
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 支持时间戳和自定义格式的LocalDate返回序列化
 * @author SummerRain
 *
 */
public class LocalDateDeserializer
    extends com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer {

  private static final long serialVersionUID = 1L;

  public LocalDateDeserializer(DateTimeFormatter dtf) {
    super(dtf);
  }

  public LocalDateDeserializer(
      com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer base, DateTimeFormatter dtf) {
    super(base, dtf);
  }

  protected LocalDateDeserializer(
      com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer base, Boolean leniency) {
    super(base, leniency);
  }

  protected LocalDateDeserializer(
      com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer base, JsonFormat.Shape shape) {
    super(base, shape);
  }

  @Override
  protected com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer withDateFormat(DateTimeFormatter dtf) {
    return new LocalDateDeserializer(this, dtf);
  }

  @Override
  protected com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer withLeniency(Boolean leniency) {
    return new LocalDateDeserializer(this, leniency);
  }

  @Override
  protected com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer withShape(JsonFormat.Shape shape) { return new LocalDateDeserializer(this, shape); }


  @Override
  public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                  IOException {
    if (parser.hasToken(JsonToken.VALUE_STRING) && StringUtils.isNumeric(parser.getText().trim())) {
      return fromString(parser, context, parser.getText());
    }
    return super.deserialize(parser, context);
  }

  private LocalDate fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
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
    return Instant.ofEpochMilli(Long.parseLong(string)).atZone(ZoneOffset.ofHours(8)).toLocalDate();
  }


}

