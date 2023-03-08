package com.ezic.controller;

import com.ezic.global.domain.ErrorResponse;
import com.ezic.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class TestControllerAdvice {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity serverErrorHandler(RuntimeException e) {
        log.error("{}", e.getStackTrace()[0]);
        return ErrorResponse.toErrorResponse(ErrorCode.SERVER_ERROR);
    }
}