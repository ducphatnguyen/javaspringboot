package com.example.demo.exception;

import com.example.demo.dto.response.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandle {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public MessageDto aHandlException(Exception ex, WebRequest request){
        ex.getLocalizedMessage();
        log.info(ex.getMessage());
        return new MessageDto(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessageDto handleException(Exception ex, WebRequest request){
        log.info(ex.getMessage().concat(ex.getLocalizedMessage()));
        ex.printStackTrace();
        return new MessageDto(1000, ex.getLocalizedMessage());
    }
}
