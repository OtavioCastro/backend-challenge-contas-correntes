package com.challenge.backend.runthebank.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record TransferBetweenAccountsRequestDTO(
        @NotBlank(message = "O número da conta que fará a transferência não pode ser vazio")
        Long agencyDebit,
        @NotBlank(message = "O número da conta que receberá a transferência não pode ser vazio")
        Long agencyCredit,
        @NotBlank(message = "O valor da transferência não pode ser vazio")
        Double transferValue) {
}
