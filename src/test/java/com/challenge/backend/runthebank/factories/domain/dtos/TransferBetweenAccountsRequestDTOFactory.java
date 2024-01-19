package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.TransferBetweenAccountsRequestDTO;

public class TransferBetweenAccountsRequestDTOFactory {

    public static TransferBetweenAccountsRequestDTO createTransferBetweenAccountsRequestDTO(Long agencyDebit, Long agencyCredit, Double transferValue){
        return new TransferBetweenAccountsRequestDTO(agencyDebit, agencyCredit, transferValue);
    }
}
