package com.challenge.backend.runthebank.gateway;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;

import java.util.UUID;

public interface AccountGateway {
    Account saveAccount(Account account);
    Account getAccount(Long agency);
    void updateAccount(AccountUpdateDTO accountUpdateDTO);
    void updateAccountBalance(Account account);
    void deleteAccount(UUID accountId);
}
