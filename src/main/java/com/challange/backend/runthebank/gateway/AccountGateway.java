package com.challange.backend.runthebank.gateway;

import com.challange.backend.runthebank.domain.Costumer;
import com.challange.backend.runthebank.domain.dtos.AccountSaveDTO;
import com.challange.backend.runthebank.domain.dtos.AccountUpdateDTO;

public interface AccountGateway {
    void saveAccount(AccountSaveDTO accountSaveDTO);
    Costumer getAccount(Long agency);
    void updateAccount(AccountUpdateDTO accountUpdateDTO);
    void deleteAccount(Long agency);
}
