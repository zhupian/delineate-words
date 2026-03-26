package com.jiang.delineatewordsbacked.exception;

import com.jiang.delineatewordsbacked.common.BaseResponse;
import com.jiang.delineatewordsbacked.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一处理参数校验类异常，保持前端可直接读取 message 字段。
     * 这里单独拦截 IllegalArgumentException，避免它被更宽泛的 RuntimeException 分支吞掉。
     *
     * @param e 参数异常
     * @return 统一错误响应
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<?> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.warn("IllegalArgumentException", e);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
