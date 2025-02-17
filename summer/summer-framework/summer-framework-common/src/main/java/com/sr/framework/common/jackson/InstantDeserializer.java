package com.sr.framework.common.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:21
 * @Description:
 */
public class InstantDeserializer extends com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer<Instant> {

  private static final long serialVersionUID = 1L;

  protected InstantDeserializer(Class<Instant> supportedType, DateTimeFormatter formatter,
                                Function<TemporalAccessor, Instant> parsedToValue, Function<com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.FromIntegerArguments, Instant> fromMilliseconds,
                                Function<com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.FromDecimalArguments, Instant> fromNanoseconds, BiFunction<Instant, ZoneId, Instant> adjust,
                                boolean replaceZeroOffsetAsZ) {
    super(supportedType, formatter, parsedToValue, fromMilliseconds, fromNanoseconds, adjust, replaceZeroOffsetAsZ);
  }

  DateTimeFormatter dtf;

  public InstantDeserializer(DateTimeFormatter dtf) {
    super(com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.INSTANT, DateTimeFormatter.ISO_INSTANT);
    this.dtf = dtf;
  }
  public InstantDeserializer(com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer<Instant> base, DateTimeFormatter dtf) {
    super(base, DateTimeFormatter.ISO_INSTANT);
    this.dtf = dtf;
  }

  protected InstantDeserializer(com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer<Instant> base, Boolean leniency) {
    super(base, leniency);
  }

  @Override
  protected InstantDeserializer withDateFormat(DateTimeFormatter dtf) {
    return new InstantDeserializer(this, dtf);
  }

  @Override
  protected InstantDeserializer withLeniency(Boolean leniency) {
    return new InstantDeserializer(this, leniency);
  }

  @Override
  protected InstantDeserializer withShape(JsonFormat.Shape shape) { return this; }


  @Override
  public Instant deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    if (parser.hasToken(JsonToken.VALUE_STRING) && null != parser.getText() && parser.getText().length() > 0) {
      if (StringUtils.isNumeric(parser.getText().trim())) {
        return fromString(parser, context, parser.getText());
      }
      if (dtf != null) {
        try {
          return LocalDateTime.parse(parser.getText(), dtf).toInstant(ZoneOffset.ofHours(8));
        }catch(Exception e) {}
      }
    }
    return super.deserialize(parser, context);
  }

  private Instant fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
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
    return Instant.ofEpochMilli(Long.parseLong(string)).atZone(ZoneOffset.ofHours(8)).toInstant();
  }

}