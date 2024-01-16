package com.challenge.backend.runthebank.domain.dtos;

public record AccountUpdateDTO(
        Long agency,
        Long newAgency
) {
}
