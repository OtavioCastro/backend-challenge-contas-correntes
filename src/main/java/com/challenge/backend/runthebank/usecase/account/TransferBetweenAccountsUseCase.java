package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.dtos.TransferBetweenAccountsResponseDTO;

public interface TransferBetweenAccountsUseCase {
    TransferBetweenAccountsResponseDTO execute(Long agencyDebit, Long agencyCredit, Double valueTransfer);
}
