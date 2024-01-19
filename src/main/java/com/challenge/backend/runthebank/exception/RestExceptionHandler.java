package com.challenge.backend.runthebank.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers, HttpStatusCode status,
                                                               WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiException(status, error, ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationDTO validationDTO = new ValidationDTO(new HashMap<>());
        fieldErrors.forEach(
                erro -> validationDTO.getErros().put(erro.getField(), erro.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(validationDTO.getErros());
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
