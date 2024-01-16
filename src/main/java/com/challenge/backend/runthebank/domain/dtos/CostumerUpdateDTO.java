package com.challenge.backend.runthebank.domain.dtos;

public record CostumerUpdateDTO(
        String document,
        String name,
        String address,
        String password
) {
}
