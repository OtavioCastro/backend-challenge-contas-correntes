package com.challenge.backend.runthebank.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers, HttpStatusCode status,
                                                               WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiException(status, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        return buildResponseEntity(new ApiException(ex.getStatusCode(), ex.getStatusText(), ex));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        return buildResponseEntity(new ApiException(ex.getStatusCode(), ex.getStatusText(), ex));
    }
}
