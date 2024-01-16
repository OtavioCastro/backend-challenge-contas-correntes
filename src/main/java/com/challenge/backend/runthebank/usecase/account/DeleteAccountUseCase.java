package com.challenge.backend.runthebank.usecase.account;

import java.util.UUID;

public interface DeleteAccountUseCase {
    void execute(UUID accountId);
}
