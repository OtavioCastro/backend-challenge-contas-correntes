package com.challenge.backend.runthebank.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AccountUpdateDTO(
        @NotBlank(message = "O número da conta não pode ser vazio")
        Long agency,
        @NotBlank(message = "O novo número da conta não pode ser vazio")
        Long newAgency
) {
}
