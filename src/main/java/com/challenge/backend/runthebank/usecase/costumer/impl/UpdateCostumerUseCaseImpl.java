package com.challenge.backend.runthebank.usecase.costumer.impl;

import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.UpdateCostumerUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateCostumerUseCaseImpl implements UpdateCostumerUseCase {

    private final CostumerGateway costumerGateway;

    public UpdateCostumerUseCaseImpl(CostumerGateway costumerGateway) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public void execute(CostumerUpdateDTO costumerUpdateDTO) {
        costumerGateway.updateCostumer(costumerUpdateDTO);
    }
}
