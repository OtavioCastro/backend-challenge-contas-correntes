package com.challange.backend.runthebank.usecase.costumer;

import com.challange.backend.runthebank.domain.dtos.CostumerUpdateDTO;

public interface UpdateCostumerUseCase {
    void execute(CostumerUpdateDTO costumerUpdateDTO);
}
