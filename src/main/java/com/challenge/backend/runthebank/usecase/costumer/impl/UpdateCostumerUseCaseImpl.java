package com.challenge.backend.runthebank.usecase.costumer.impl;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.UpdateCostumerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.isNull;

@Service
public class UpdateCostumerUseCaseImpl implements UpdateCostumerUseCase {

    private final CostumerGateway costumerGateway;

    public UpdateCostumerUseCaseImpl(CostumerGateway costumerGateway) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public void execute(CostumerUpdateDTO costumerUpdateDTO) {
        Costumer costumer = costumerGateway.getCostumer(costumerUpdateDTO.document());

        if(isNull(costumer)){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não cadastrada.");
        }

        costumerGateway.updateCostumer(costumerUpdateDTO);
    }
}
