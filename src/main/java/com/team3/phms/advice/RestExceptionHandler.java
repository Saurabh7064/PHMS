package com.team3.phms.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * default catch
     * @param e the e
     * @return Response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<String> exception(Exception e) {
        log.error("ex={}", e.getMessage(), e);
        return Response.fail(Code.RC500.getCode(),e.getMessage());
    }

}