//package com.sr.framework.common.result;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * 分页响应结构体
// *
// * @author SummerRain
// * @Date 2024/9/13 16:25
// * @Description:
// */
//@Data
//public class PageResult<T> implements Serializable {
//
//    private String code;
//
//    private Data<T> data;
//
//    private String message;
//
//    public static <T> PageResult<T> success(IPage<T> page) {
//        PageResult<T> result = new PageResult<>();
//        result.setCode(ResultCode.SUCCESS.getCode());
//
//        Data data = new Data<T>();
//        data.setList(page.getRecords());
//        data.setTotal(page.getTotal());
//
//        result.setData(data);
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result;
//    }
//
//  public static <T> PageResult<T> failed(ResultCode resultCode) {
//    return result(resultCode.getCode(), resultCode.getMessage(), null);
//  }
//
//    public static <T> PageResult<T> failed(ResultCode resultCode, String msg) {
//        return result(resultCode.getCode(), msg, null);
//    }
//
//    private static <T> PageResult<T> result(String code, String msg, IPage<T> page) {
//        PageResult<T> result = new PageResult<>();
//        result.setCode(code);
//
//        if(null != page) {
//            Data data = new Data<T>();
//            data.setList(page.getRecords());
//            data.setTotal(page.getTotal());
//
//            result.setData(data);
//        }
//
//        result.setMessage(msg);
//        return result;
//    }
//
//    @lombok.Data
//    public static class Data<T> {
//
//        private List<T> list;
//
//        private long total;
//
//    }
//
//}
