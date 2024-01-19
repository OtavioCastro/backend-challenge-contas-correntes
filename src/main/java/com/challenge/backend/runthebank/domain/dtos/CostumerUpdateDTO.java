package com.challenge.backend.runthebank.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CostumerUpdateDTO(
        @NotBlank(message = "O número do documento não pode ser vazio")
        String document,
        String name,
        String address,
        String password
) {
}
