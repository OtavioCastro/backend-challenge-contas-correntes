package com.challenge.backend.runthebank.controller.converter;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountResponseDTOConverter {

    public AccountResponseDTO convert(Account account){
        return  new AccountResponseDTO(
                account.getAccountId(),
                account.getAgency(),
                account.getAccountStatus(),
                account.getBalance()
        );
    }

}
