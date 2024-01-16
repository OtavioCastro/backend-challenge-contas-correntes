package com.challenge.backend.runthebank.domain.dtos;

public record AccountBalanceRequestDTO(
        Long agency,
        Double value
) {
}
