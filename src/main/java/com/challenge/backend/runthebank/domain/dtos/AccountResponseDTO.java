package com.challenge.backend.runthebank.domain.dtos;

import com.challenge.backend.runthebank.domain.enums.AccountStatus;

import java.util.UUID;

public record AccountResponseDTO(
        UUID accountId,
        Long agency,
        AccountStatus accountStatus,
        Double balance
) {
}
