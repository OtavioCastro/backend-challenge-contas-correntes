package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.DepositUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.isNull;

@Service
public class DepositUseCaseImpl implements DepositUseCase {

    private final AccountGateway accountGateway;

    public DepositUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Transactional
    @Override
    public AccountBalanceResponseDTO execute(AccountBalanceRequestDTO accountBalanceRequestDTO) {
        Account account = accountGateway.getAccount(accountBalanceRequestDTO.agency());

        if(isNull(account)){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Conta não cadastrada.");
        }

        account.credit(accountBalanceRequestDTO.value());
        accountGateway.updateAccountBalance(account);
        return new AccountBalanceResponseDTO(account.getAgency(),
                account.getBalance());
    }
}
