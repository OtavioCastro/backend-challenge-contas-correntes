package com.challenge.backend.runthebank.usecase.costumer.impl;

import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.DeleteCostumerUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCostumerUseCaseImpl implements DeleteCostumerUseCase {

    private final CostumerGateway costumerGateway;

    public DeleteCostumerUseCaseImpl(CostumerGateway costumerGateway) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public void execute(UUID costumerId) {
        costumerGateway.deleteCostumer(costumerId);
    }
}
