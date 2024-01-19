package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.AccountTransferDTO;

public class AccountTransferDTOFactory {

    public static AccountTransferDTO createAccountTransferDTO(Long agency, Double balance, boolean messageSent){
        return new AccountTransferDTO(agency, balance, messageSent);
    }
}
