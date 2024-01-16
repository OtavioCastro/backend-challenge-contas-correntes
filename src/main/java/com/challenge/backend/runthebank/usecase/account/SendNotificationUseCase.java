package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;

public interface SendNotificationUseCase {
    Boolean execute(Account account);
}
