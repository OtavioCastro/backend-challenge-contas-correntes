package com.challenge.backend.runthebank.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class ApiException {
    private HttpStatusCode status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String mensagem;
    private String debugMessage;

    private ApiException() {
        timestamp = LocalDateTime.now();
    }

    ApiException(HttpStatusCode status) {
        this();
        this.status = status;
    }

    ApiException(HttpStatusCode status, Throwable ex) {
        this();
        this.status = status;
        this.mensagem = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiException(HttpStatusCode status, String message, Throwable ex) {
        this();
        this.status = status;
        this.mensagem = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
