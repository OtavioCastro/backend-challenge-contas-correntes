package com.challenge.backend.runthebank.usecase.costumer.impl;

import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.SaveCostumerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.nonNull;

@Service
public class SaveCostumerUseCaseImpl implements SaveCostumerUseCase {

    private final CostumerGateway costumerGateway;
    private final CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter;

    public SaveCostumerUseCaseImpl(CostumerGateway costumerGateway,
                                   CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter) {
        this.costumerGateway = costumerGateway;
        this.toCostumerResponseDTOConverter = toCostumerResponseDTOConverter;
    }

    @Override
    public Costumer execute(CostumerSaveDTO costumerSaveDTO) {
        validateExistentCostumer(costumerSaveDTO.document());
        Costumer costumer = new Costumer(
                costumerSaveDTO.name(),
                costumerSaveDTO.document(),
                costumerSaveDTO.address(),
                costumerSaveDTO.password(),
                costumerSaveDTO.tipoPessoa()
        );
       return costumerGateway.saveCostumer(costumer);
    }

    private void validateExistentCostumer(String costumerDocument) {
        if(nonNull(costumerGateway.getCostumer(costumerDocument))){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um cadastro para este documento.");
        }
    }
}
