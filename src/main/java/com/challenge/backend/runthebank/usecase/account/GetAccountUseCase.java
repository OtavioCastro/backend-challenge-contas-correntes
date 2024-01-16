package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;

public interface GetAccountUseCase {
    Account execute(Long agency);
}
