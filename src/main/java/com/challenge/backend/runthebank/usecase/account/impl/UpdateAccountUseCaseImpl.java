package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.UpdateAccountUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountGateway accountGateway;

    public UpdateAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public void execute(AccountUpdateDTO accountUpdateDTO) {
        accountGateway.updateAccount(accountUpdateDTO);
    }
}
