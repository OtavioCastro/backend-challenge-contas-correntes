package com.challenge.backend.runthebank.domain.dtos;

public record TransferBetweenAccountsResponseDTO(
        AccountTransferDTO accountDebit,
        AccountTransferDTO accountCredit,
        Double transferValue
) {
}
