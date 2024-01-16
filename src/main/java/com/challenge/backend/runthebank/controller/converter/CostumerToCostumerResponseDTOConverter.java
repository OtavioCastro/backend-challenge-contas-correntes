package com.challenge.backend.runthebank.controller.converter;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.AccountResponseDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CostumerToCostumerResponseDTOConverter {

    public CostumerResponseDTO convert(Costumer costumerSave){
        return new CostumerResponseDTO(
                costumerSave.getCostumerId(),
                costumerSave.getName(),
                costumerSave.getDocument(),
                costumerSave.getAddress(),
                costumerSave.getTipoPessoa(),
                buildAccountsResponseDTO(costumerSave.getAccounts())
        );
    }

    private List<AccountResponseDTO> buildAccountsResponseDTO(List<Account> accounts) {
        return accounts.stream()
                .map(account -> new AccountResponseDTO(
                        account.getAccountId(),
                        account.getAgency(),
                        account.getAccountStatus(),
                        account.getBalance()))
                .collect(Collectors.toList());
    }
}
