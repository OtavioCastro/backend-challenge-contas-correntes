package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.WithdrawUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class WithdrawUseCaseImpl implements WithdrawUseCase {

    private final AccountGateway accountGateway;

    public WithdrawUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Transactional
    @Override
    public AccountBalanceResponseDTO execute(AccountBalanceRequestDTO accountBalanceRequestDTO) {
        Account account = accountGateway.getAccount(accountBalanceRequestDTO.agency());

        if(account.getBalance().compareTo(accountBalanceRequestDTO.value()) < 0){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Saldo insuficiente para a transação");
        }
        account.debit(accountBalanceRequestDTO.value());
        accountGateway.updateAccountBalance(account);
        return new AccountBalanceResponseDTO(account.getAgency(),
                account.getBalance());
    }
}
