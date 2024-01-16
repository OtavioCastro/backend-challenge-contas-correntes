package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;

public interface DepositUseCase {
    AccountBalanceResponseDTO execute(AccountBalanceRequestDTO accountBalanceRequestDTO);
}
