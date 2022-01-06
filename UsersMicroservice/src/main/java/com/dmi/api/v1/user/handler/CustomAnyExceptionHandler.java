package com.dmi.api.v1.user.handler;

import com.dmi.api.v1.user.error.ErrorResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class CustomAnyExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAnyExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest webRequest){
        LOGGER.error("handleAnyException: Any-Exception");
        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setErrorMessage(exception.getMessage());
        errorResponseModel.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponseModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
