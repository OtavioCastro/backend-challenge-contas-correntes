package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.DeleteAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.isNull;

@Service
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountGateway accountGateway;

    public DeleteAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public void execute(Long agency) {
        Account account = accountGateway.getAccount(agency);

        if(isNull(account)){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Conta não cadastrada.");
        }

        accountGateway.deleteAccount(account.getAccountId());
    }
}
