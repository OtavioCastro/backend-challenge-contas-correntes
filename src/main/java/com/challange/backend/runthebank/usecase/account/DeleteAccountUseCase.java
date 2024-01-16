package com.challange.backend.runthebank.usecase.account;

import java.util.UUID;

public interface DeleteAccountUseCase {
    void execute(UUID accountId);
}
