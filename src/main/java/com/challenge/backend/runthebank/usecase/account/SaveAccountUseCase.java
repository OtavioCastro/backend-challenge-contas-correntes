package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountSaveDTO;

public interface SaveAccountUseCase {
    Account execute(AccountSaveDTO accountSaveDTO);
}
