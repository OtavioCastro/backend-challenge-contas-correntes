package com.challenge.backend.runthebank.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AccountSaveDTO(
        @NotBlank(message = "O número da conta não pode ser vazio")
        Long agency,
        @NotBlank(message = "O número do documento não pode ser vazio")
        String document){
}
