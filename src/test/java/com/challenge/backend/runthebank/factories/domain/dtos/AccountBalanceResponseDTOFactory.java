package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;

public class AccountBalanceResponseDTOFactory {

    public static AccountBalanceResponseDTO createAccountBalanceResponseDTO(Long agency, Double value){
        return new AccountBalanceResponseDTO(agency, value);
    }
}
