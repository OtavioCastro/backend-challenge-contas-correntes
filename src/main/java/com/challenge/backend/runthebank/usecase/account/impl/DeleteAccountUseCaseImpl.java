package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.DeleteAccountUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountGateway accountGateway;

    public DeleteAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public void execute(UUID accountId) {
        accountGateway.deleteAccount(accountId);
    }
}
