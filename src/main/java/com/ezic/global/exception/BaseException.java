package com.ezic.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    public final static BaseException MEMBER_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.MEMBER_NOT_FOUND);
    public final static BaseException AUTH_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.AUTH_NOT_FOUND);
    public final static BaseException AUTH_ALREADY_DONE_EXCEPTION = new BaseException(ErrorCode.AUTH_ALREADY_DONE);

    public final static BaseException RESOURCE_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.RESOURCE_NOT_FOUND);

    private final ErrorCode errorCode;
}
