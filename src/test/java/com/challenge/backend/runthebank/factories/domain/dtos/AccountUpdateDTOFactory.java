package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;

public class AccountUpdateDTOFactory {

    public static AccountUpdateDTO createAccountUpdateDTO(Long olderAgency, Long newAgency){
        return new AccountUpdateDTO(olderAgency, newAgency);
    }
}
