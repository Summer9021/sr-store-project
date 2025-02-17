package com.sr.framework.common.jackson;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:19
 * @Description:
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializerBase;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serializer for Java 8 temporal {@link Instant}s, {@link OffsetDateTime}, and {@link ZonedDateTime}s.
 *
 * @author SummerRain
 * @since 2.2
 */
public class InstantSerializer extends com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer
{
  private static final long serialVersionUID = 1L;


  public InstantSerializer(Boolean useTimestamp, DateTimeFormatter formatter) {
    this(com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer.INSTANCE, useTimestamp, null, formatter);
  }

  public InstantSerializer(com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer base,
                           Boolean useTimestamp, DateTimeFormatter formatter) {
    this(base, useTimestamp, null, formatter);
  }

  protected InstantSerializer(com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer base,
                              Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter) {
    super(base, useTimestamp, useNanoseconds, formatter);
  }

  @Override
  protected InstantSerializerBase<Instant> withFormat(Boolean useTimestamp,
                                                      DateTimeFormatter formatter, JsonFormat.Shape shape) {
    return new InstantSerializer(this, useTimestamp, formatter);
  }

  @Override
  protected InstantSerializerBase<?> withFeatures(Boolean writeZoneId, Boolean writeNanoseconds) {
    return new InstantSerializer(this, _useTimestamp, writeNanoseconds, _formatter);
  }
}
