package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.AccountSaveDTO;

public class AccountSaveDTOFactory {

    public static AccountSaveDTO createAccountSaveDTO(Long agency, String document){
        return new AccountSaveDTO(agency, document);
    }
}
