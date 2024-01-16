package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;

public interface UpdateAccountUseCase {
    void execute(AccountUpdateDTO accountUpdateDTO);
}
