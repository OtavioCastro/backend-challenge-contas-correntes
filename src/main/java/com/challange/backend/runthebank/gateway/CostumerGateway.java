package com.challange.backend.runthebank.gateway;

import com.challange.backend.runthebank.domain.Costumer;
import com.challange.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challange.backend.runthebank.domain.dtos.CostumerUpdateDTO;

public interface CostumerGateway {
    void saveCostumer(CostumerSaveDTO costumerSaveDTO);
    Costumer getCostumer(String document);
    void updateCostumer(CostumerUpdateDTO costumerUpdateDTO);
    void deleteCostumer(String document);
}
