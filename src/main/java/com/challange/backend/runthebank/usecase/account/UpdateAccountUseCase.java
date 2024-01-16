package com.challange.backend.runthebank.usecase.account;

import com.challange.backend.runthebank.domain.dtos.AccountUpdateDTO;

public interface UpdateAccountUseCase {
    void execute(AccountUpdateDTO accountUpdateDTO);
}
