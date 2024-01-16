package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.domain.Costumer;

public interface GetCostumerUseCase {
    Costumer execute(String document);
}
