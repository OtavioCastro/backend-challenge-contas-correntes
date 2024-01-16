package com.challange.backend.runthebank.usecase.account;

import com.challange.backend.runthebank.domain.Account;

public interface GetAccountUseCase {
    Account getAccount(Long agency);
}
