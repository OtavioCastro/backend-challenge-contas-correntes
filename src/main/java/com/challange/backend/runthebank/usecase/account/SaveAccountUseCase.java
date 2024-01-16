package com.challange.backend.runthebank.usecase.account;

import com.challange.backend.runthebank.domain.Account;
import com.challange.backend.runthebank.domain.dtos.AccountSaveDTO;

public interface SaveAccountUseCase {
    Account saveAccount(AccountSaveDTO accountSaveDTO);
}
