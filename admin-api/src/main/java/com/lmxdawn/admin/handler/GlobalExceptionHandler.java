package com.lmxdawn.admin.handler;

import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.exception.JsonException;
import com.lmxdawn.admin.utils.ResultVOUtil;
import com.lmxdawn.admin.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 错误回调
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // 拦截API异常
    @ExceptionHandler(value = JsonException.class)
    public ResultVO handlerJsonException(JsonException e) {
        // 返回对应的错误信息
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    // 拦截API异常
    @ExceptionHandler(value = RuntimeException.class)
    public ResultVO handlerRuntimeException(RuntimeException e) {
        // 返回对应的错误信息
        return ResultVOUtil.error(ResultEnum.NOT_NETWORK);
    }
    
}
