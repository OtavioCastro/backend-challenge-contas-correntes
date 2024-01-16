package com.challenge.backend.runthebank.domain.dtos;

import com.challenge.backend.runthebank.domain.enums.TipoPessoa;

import java.util.List;
import java.util.UUID;

public record CostumerResponseDTO(
        UUID costumerId,
        String name,
        String document,
        String address,
        TipoPessoa tipoPessoa,
        List<AccountResponseDTO> accounts
) {
}
