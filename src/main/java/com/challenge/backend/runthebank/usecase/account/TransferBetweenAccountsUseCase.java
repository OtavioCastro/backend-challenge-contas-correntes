package com.challenge.backend.runthebank.usecase.account;

import java.math.BigDecimal;

public interface TransferBetweenAccountsUseCase {
    void execute(Long agencyDebit, Long agencyCredit, Double valueTransfer);
}
