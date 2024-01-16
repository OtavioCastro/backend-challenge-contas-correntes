package com.challenge.backend.runthebank.gateway;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;

import java.util.UUID;

public interface CostumerGateway {
    Costumer saveCostumer(Costumer costumer);
    Costumer getCostumer(String document);
    void updateCostumer(CostumerUpdateDTO costumerUpdateDTO);
    void deleteCostumer(UUID costumerId);
}
