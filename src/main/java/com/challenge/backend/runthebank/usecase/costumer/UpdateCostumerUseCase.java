package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;

public interface UpdateCostumerUseCase {
    void execute(CostumerUpdateDTO costumerUpdateDTO);
}
