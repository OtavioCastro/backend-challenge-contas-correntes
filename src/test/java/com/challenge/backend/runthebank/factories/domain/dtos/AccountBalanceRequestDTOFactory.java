package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;

public class AccountBalanceRequestDTOFactory {

    public static AccountBalanceRequestDTO createAccountBalanceRequestDTO(Long agency, Double value){
        return new AccountBalanceRequestDTO(agency, value);
    }
}
