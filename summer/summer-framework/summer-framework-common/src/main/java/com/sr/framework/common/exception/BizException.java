package com.sr.framework.common.exception;

import com.sr.framework.common.result.IResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author SummerRain
 * @since 2022/7/31
 */
@Getter
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMessage());
        this.resultCode = errorCode;
    }

    public BizException(IResultCode errorCode, String message) {
        super(message);
        this.resultCode = errorCode;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }


}
