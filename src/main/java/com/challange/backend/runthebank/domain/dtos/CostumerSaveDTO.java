package com.challange.backend.runthebank.domain.dtos;

import com.challange.backend.runthebank.domain.enums.TipoPessoa;

public record CostumerSaveDTO(
        String name,
        String document,
        String address,
        String password,
        TipoPessoa tipoPessoa
) {
}
