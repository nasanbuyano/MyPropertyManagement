package com.miu.propertymanagement.integration.exception;

import com.miu.propertymanagement.domain.dto.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {
        System.out.println("ExceptionHandler");
        Map<String, Object> fieldErrorMap = new HashMap<>();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrorList) {
            fieldErrorMap.put(error.getField(), error.getDefaultMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("iSuccess", false);
        map.put("data", null);
        map.put("status code", HttpStatus.BAD_REQUEST);
        map.put("fieldError", fieldErrorMap);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PMSException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequestException(PMSException exception) {
        return new ResponseEntity<>(new CustomErrorType(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
