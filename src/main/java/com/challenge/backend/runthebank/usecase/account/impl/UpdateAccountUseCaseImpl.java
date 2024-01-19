package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.UpdateAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.isNull;

@Service
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountGateway accountGateway;

    public UpdateAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public void execute(AccountUpdateDTO accountUpdateDTO) {
        Account account = accountGateway.getAccount(accountUpdateDTO.agency());


        if(isNull(account)){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Conta não cadastrada.");
        }

        accountGateway.updateAccount(accountUpdateDTO);
    }
}
