package com.challenge.backend.runthebank.factories.domain.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;

import java.util.UUID;

public class AccountFactory {

    public static Account createAccount(){
        return new Account(
                UUID.fromString("40645480-6dec-4a75-895e-1d9797663e94"),
                12345L,
                10.0,
                AccountStatus.ACTIVE,
                CostumerFactory.createCostumer()
        );
    }

    public static Account createAccountToCredit(){
        return new Account(
                UUID.fromString("d2cd9976-0e39-42cc-9177-a20afab32823"),
                56789L,
                0.0,
                AccountStatus.ACTIVE,
                CostumerFactory.createCostumerToTransfer()
        );
    }

    public static Account createDeactiveAccount(){
        return new Account(
                UUID.fromString("3ea5200d-0530-40a8-8a1c-601d8f923bcd"),
                858585L,
                0.0,
                AccountStatus.DEACTIVE,
                CostumerFactory.createCostumerToTransfer()
        );
    }
}
