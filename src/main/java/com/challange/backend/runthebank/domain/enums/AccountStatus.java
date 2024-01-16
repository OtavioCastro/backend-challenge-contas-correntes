package com.challange.backend.runthebank.domain.enums;

public enum AccountStatus {
    ATIVE(true),
    DEACTIVE(false);

    private boolean active;

    AccountStatus(boolean active) {
    }
}
