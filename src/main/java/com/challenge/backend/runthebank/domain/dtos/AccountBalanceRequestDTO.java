package com.challenge.backend.runthebank.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AccountBalanceRequestDTO(
        @NotBlank(message = "O número da conta não pode ser vazio")
        Long agency,
        @NotBlank(message = "O valor solicitado não pode ser vazio")
        Double value
) {
}
