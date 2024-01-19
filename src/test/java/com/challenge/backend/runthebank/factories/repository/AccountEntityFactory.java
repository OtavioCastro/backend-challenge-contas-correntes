package com.challenge.backend.runthebank.factories.repository;

import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import com.challenge.backend.runthebank.repository.entity.AccountEntity;

import java.util.UUID;

public class AccountEntityFactory {

    public static AccountEntity createAccountEntity(){
        return new AccountEntity(
                UUID.fromString("40645480-6dec-4a75-895e-1d9797663e94"),
                12345L,
                120.00,
                AccountStatus.ACTIVE
        );
    }
}
