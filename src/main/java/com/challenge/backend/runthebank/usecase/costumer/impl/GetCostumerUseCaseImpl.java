package com.challenge.backend.runthebank.usecase.costumer.impl;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Optional.ofNullable;

@Service
public class GetCostumerUseCaseImpl implements GetCostumerUseCase {

    private final CostumerGateway costumerGateway;


    public GetCostumerUseCaseImpl(CostumerGateway costumerGateway,
                                  CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public Costumer execute(String document) {
        return ofNullable(costumerGateway.getCostumer(document))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }
}
