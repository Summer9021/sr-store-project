package com.sr.framework.common.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.*;

/**
 * @author SummerRain
 * @Date 2024/9/12 11:28
 * @Description:
 */
public class ScriptObjectMirrorUtil {

  /***
   * 默认转json会出现[1,2,3] => {"0":1,"1":2,"2":3}
   * 处理后{"0":1,"1":2,"2":3} => [1,2,3]
   *
   * @param mirror java js对象
   * @return java.lang.Object
   **/
  public static Object toJavaArray(ScriptObjectMirror mirror) {
    if (mirror.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    if (mirror.isArray()) {
      List<Object> list = new ArrayList<>();
      for (Map.Entry<String, Object> entry : mirror.entrySet()) {
        Object result = entry.getValue();
        if (result instanceof ScriptObjectMirror) {
          list.add(toJavaArray((ScriptObjectMirror) result));
        } else {
          list.add(result);
        }
      }
      return list;
    }

    Map<String, Object> map = new HashMap<>();
    for (Map.Entry<String, Object> entry : mirror.entrySet()) {
      Object result = entry.getValue();
      if (result instanceof ScriptObjectMirror) {
        map.put(entry.getKey(), toJavaArray((ScriptObjectMirror) result));
      } else {
        map.put(entry.getKey(), result);
      }
    }
    return map;
  }
}
