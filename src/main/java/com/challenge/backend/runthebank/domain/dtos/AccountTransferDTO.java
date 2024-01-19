package com.challenge.backend.runthebank.domain.dtos;

public record AccountTransferDTO(
        Long agency,
        Double balance,
        boolean notificationSend
) {
}
