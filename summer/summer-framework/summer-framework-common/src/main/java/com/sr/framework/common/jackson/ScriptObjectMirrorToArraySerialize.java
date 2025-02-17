package com.sr.framework.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sr.framework.common.utils.ScriptObjectMirrorUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.IOException;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:27
 * @Description:
 */
public class ScriptObjectMirrorToArraySerialize extends JsonSerializer<ScriptObjectMirror> {

  /***
   * ScriptObjectMirror 序列化
   *
   * @param value
   * @param gen
   * @param serializers
   * @return void
   **/
  @Override
  public void serialize(ScriptObjectMirror value, JsonGenerator gen, SerializerProvider serializers) throws
                                                                                                     IOException {
    gen.writeObject(ScriptObjectMirrorUtil.toJavaArray(value));
  }
}
