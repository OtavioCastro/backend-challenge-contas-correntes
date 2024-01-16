package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.GetAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final AccountGateway accountGateway;

    public GetAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(Long agency) {
        return Optional.ofNullable(accountGateway.getAccount(agency))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Conta bancária não encontrada"));
    }
}
