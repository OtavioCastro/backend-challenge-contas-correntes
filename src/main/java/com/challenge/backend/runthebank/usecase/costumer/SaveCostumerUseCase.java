package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;

public interface SaveCostumerUseCase {
    Costumer execute(CostumerSaveDTO costumerSaveDTO);
}
