package com.challange.backend.runthebank.usecase.costumer;

import com.challange.backend.runthebank.domain.dtos.CostumerSaveDTO;

public interface SaveCostumerUseCase {
    void execute(CostumerSaveDTO costumerSaveDTO);
}
