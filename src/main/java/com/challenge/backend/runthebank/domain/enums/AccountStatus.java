package com.challenge.backend.runthebank.domain.enums;

public enum AccountStatus {
    ACTIVE(true),
    DEACTIVE(false);

    private boolean active;

    AccountStatus(boolean active) {
    }
}
