package com.challange.backend.runthebank.usecase.account;

public interface TransferBetweenAccountsUseCase {
    void execute(Long agencyDebit, Long agencyCredit);
}
