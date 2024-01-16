package com.challenge.backend.runthebank.domain.dtos;

public record TransferBetweenAccountsDTO(Long agencyDebit,
                                         Long agencyCredit,
                                         Double transferValue) {
}
