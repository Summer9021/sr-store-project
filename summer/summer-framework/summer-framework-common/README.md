# common集成包

summer-framework-common项目以jar包的方式提供 1，基础model 2，上下文信息 3，全局异常处理 4，feign配置 5，jackson序列化工具 6，日志工具
7，mybatis工具等

1.constant:项目公共常量和网关常量
2.current:获取当前登录用户的工具类
3.exception:公共全局异常处理和自定义异常
4.feign:feign常用配置,错误处理,日志处理,请求头处理等
5.jackson:jackson转换一些基础类
6.log:logback-spring.xml依赖的日志处理类
7.model:entity基础类,基础分页请求对象,多租户实体抽象类,枚举通用接口
8.result:统一响应结构体和统一分页响应结构体
9.utils:Bean工具类


---

## 集成方式

- 添加maven依赖（需要本地先install）

```xml

<dependency>
    <groupId>com.sr</groupId>
    <artifactId>summer-framework-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

- 项目配置

```ymal
# Feign客户端的日志级别 默认BASIC 可选配置 
# NONE‌：不记录任何日志。
BASIC‌：仅记录请求方法、URL、响应状态码和执行时间。
HEADERS‌：记录BASIC级别的信息，并附加请求和响应的头信息。
FULL‌：记录所有请求和响应的详细信息，包括请求头、请求体、响应体

feign:
  client:
    config:
      default:
        loggerLevel: BASIC
```

---

## 使用说明

参考demo工程，一半内置demo工程同步生成
