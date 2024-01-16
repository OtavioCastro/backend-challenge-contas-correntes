package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.GetAccountUseCase;
import com.challenge.backend.runthebank.usecase.account.SendNotificationUseCase;
import com.challenge.backend.runthebank.usecase.account.TransferBetweenAccountsUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class TransferBetweenAccountsUseCaseImpl implements TransferBetweenAccountsUseCase {

    private final AccountGateway accountGateway;
    private final GetAccountUseCase getAccountUseCase;
    private final SendNotificationUseCase sendNotificationUseCase;

    public TransferBetweenAccountsUseCaseImpl(AccountGateway accountGateway, GetAccountUseCase getAccountUseCase, SendNotificationUseCase sendNotificationUseCase) {
        this.accountGateway = accountGateway;
        this.getAccountUseCase = getAccountUseCase;
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @Transactional
    @Override
    public void execute(Long agencyAccountDebit, Long agencyAccountCredit, Double valueTransfer) {
        Account accountToDebit = getAccountUseCase.execute(agencyAccountDebit);
        Account accountToCredit = getAccountUseCase.execute(agencyAccountCredit);


        if (accountToDebit.getBalance().compareTo(valueTransfer) < 0) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Saldo insuficiente para a transação");
        }
        accountToDebit.debit(valueTransfer);
        accountToCredit.credit(valueTransfer);
        accountGateway.updateAccountBalance(accountToDebit);
        accountGateway.updateAccountBalance(accountToCredit);

        sendNotificationUseCase.execute(accountToDebit);
        sendNotificationUseCase.execute(accountToCredit);
    }

    private void validateAccount(Account account){
        if(account.getAccountStatus().equals(AccountStatus.DEACTIVE)) throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "A conta está desativada.");
    }
}
