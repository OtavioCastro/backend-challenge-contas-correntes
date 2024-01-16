package com.challenge.backend.runthebank.usecase.costumer;

import java.util.UUID;

public interface DeleteCostumerUseCase {
    void execute(UUID costumerId);
}
