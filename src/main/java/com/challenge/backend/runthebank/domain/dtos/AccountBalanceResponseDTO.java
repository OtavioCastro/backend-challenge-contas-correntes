package com.challenge.backend.runthebank.domain.dtos;

public record AccountBalanceResponseDTO(
        Long agency,
        Double value
) {
}
