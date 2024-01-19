package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountTransferDTO;
import com.challenge.backend.runthebank.domain.dtos.TransferBetweenAccountsResponseDTO;
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
    public TransferBetweenAccountsResponseDTO execute(Long agencyAccountDebit, Long agencyAccountCredit, Double valueTransfer) {
        Account accountToDebit = getAccountUseCase.execute(agencyAccountDebit);
        validateAccount(accountToDebit);
        Account accountToCredit = getAccountUseCase.execute(agencyAccountCredit);
        validateAccount(accountToCredit);

        if (accountToDebit.getBalance().compareTo(valueTransfer) < 0) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Saldo insuficiente para a transação");
        }

        updateValues(valueTransfer, accountToDebit, accountToCredit);

        boolean notificationDebitAccount = sendNotificationUseCase.execute(accountToDebit);
        boolean notificationCreditAccount = sendNotificationUseCase.execute(accountToCredit);

        AccountTransferDTO accountDebit = createAccountTransferDTO(accountToDebit, notificationDebitAccount);
        AccountTransferDTO accountCredit = createAccountTransferDTO(accountToCredit, notificationCreditAccount);

        return createTransferBetweenAccountsDTO(valueTransfer, accountDebit, accountCredit);
    }

    private void validateAccount(Account account){
        if(account.getAccountStatus().equals(AccountStatus.DEACTIVE)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "A conta " + account.getAgency() +" está desativada.");
        }
    }

    private void updateValues(Double valueTransfer, Account accountToDebit, Account accountToCredit) {
        accountToDebit.debit(valueTransfer);
        accountToCredit.credit(valueTransfer);
        accountGateway.updateAccountBalance(accountToDebit);
        accountGateway.updateAccountBalance(accountToCredit);
    }

    private AccountTransferDTO createAccountTransferDTO(Account accountToDebit, boolean notificationDebitAccount) {
        return new AccountTransferDTO(accountToDebit.getAgency(), accountToDebit.getBalance(), notificationDebitAccount);
    }

    private TransferBetweenAccountsResponseDTO createTransferBetweenAccountsDTO(Double valueTransfer, AccountTransferDTO accountDebit, AccountTransferDTO accountCredit) {
        return new TransferBetweenAccountsResponseDTO(accountDebit, accountCredit, valueTransfer);
    }
}
