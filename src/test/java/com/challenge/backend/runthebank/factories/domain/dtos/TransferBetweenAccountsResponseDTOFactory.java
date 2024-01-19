package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.TransferBetweenAccountsResponseDTO;

public class TransferBetweenAccountsResponseDTOFactory {

    public static TransferBetweenAccountsResponseDTO createTransferBetweenAccountsDTO(){
        return new TransferBetweenAccountsResponseDTO(
                AccountTransferDTOFactory.createAccountTransferDTO(12345L, 10.00, true),
                AccountTransferDTOFactory.createAccountTransferDTO(98765L, 0.00, true),
                10.00
        );
    }

}
