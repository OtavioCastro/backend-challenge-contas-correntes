package com.challange.backend.runthebank.usecase.costumer;

import com.challange.backend.runthebank.domain.Costumer;

public interface GetCostumerUseCase {
    Costumer execute(String document);
}
