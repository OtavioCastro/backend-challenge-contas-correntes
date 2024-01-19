package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
import com.challenge.backend.runthebank.domain.dtos.AccountResponseDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerResponseDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.usecase.costumer.DeleteCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.SaveCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.UpdateCostumerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    private final SaveCostumerUseCase saveCostumerUseCase;
    private final GetCostumerUseCase getCostumerUseCase;
    private final UpdateCostumerUseCase updateCostumerUseCase;
    private final DeleteCostumerUseCase deleteCostumerUseCase;
    private final CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter;

    public CostumerController(SaveCostumerUseCase saveCostumerUseCase,
                              GetCostumerUseCase getCostumerUseCase,
                              UpdateCostumerUseCase updateCostumerUseCase,
                              DeleteCostumerUseCase deleteCostumerUseCase,
                              CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter) {
        this.saveCostumerUseCase = saveCostumerUseCase;
        this.getCostumerUseCase = getCostumerUseCase;
        this.updateCostumerUseCase = updateCostumerUseCase;
        this.deleteCostumerUseCase = deleteCostumerUseCase;
        this.toCostumerResponseDTOConverter = toCostumerResponseDTOConverter;
    }

    @Operation(summary = "Cadastra um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CostumerResponseDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Já existe um cadastro para este documento")
    })
    @PostMapping
    public ResponseEntity<CostumerResponseDTO> saveCostumer(@RequestBody @Valid CostumerSaveDTO costumerSaveDTO){
        return ResponseEntity.ok(toCostumerResponseDTOConverter.convert(saveCostumerUseCase.execute(costumerSaveDTO)));
    }

    @Operation(summary = "Obtém um cliente cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{document}")
    public ResponseEntity<CostumerResponseDTO> getCostumer(
            @NotBlank(message = "O documento do cliente não pode ser vazio")
            @PathVariable String document){
        return ResponseEntity.ok(toCostumerResponseDTOConverter.convert(getCostumerUseCase.execute(document)));
    }

    @Operation(summary = "Atualiza um cliente cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public ResponseEntity<Void> updateCostumer(@RequestBody CostumerUpdateDTO costumerUpdateDTO){
        updateCostumerUseCase.execute(costumerUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Exclui um cliente cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("{costumerId}")
    public ResponseEntity<Void> deleteCostumer(
            @NotNull(message = "id do cliente necessário para realizar esta operação")
            @PathVariable UUID costumerId){
        deleteCostumerUseCase.execute(costumerId);
        return ResponseEntity.noContent().build();
    }
}
