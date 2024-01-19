package com.challenge.backend.runthebank.exception;

import java.util.Map;

public class ValidationDTO {

    private Map<String, String> erros;

    public ValidationDTO(Map<String, String> erros) {
        this.erros = erros;
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void setErros(Map<String, String> erros) {
        this.erros = erros;
    }
}
