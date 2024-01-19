package com.challenge.backend.runthebank.domain.dtos;

import com.challenge.backend.runthebank.domain.enums.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CostumerSaveDTO(
        @NotBlank(message = "O nome do Cliente não pode ser vazio")
        String name,
        @NotBlank(message = "O documento do cliente não pode ser vazio")
        String document,
        @NotBlank(message = "O endereço não pode ser vazio")
        String address,
        @NotBlank(message = "O password não pode ser vazio")
        String password,
        @NotNull(message = "O tipo de pessoa (Física/Jurídica) deve ser selecionado")
        TipoPessoa tipoPessoa
) {
}
