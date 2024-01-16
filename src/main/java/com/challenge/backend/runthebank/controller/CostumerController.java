package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.domain.dtos.CostumerResponseDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.usecase.costumer.DeleteCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.SaveCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.UpdateCostumerUseCase;
import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
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

    @PostMapping
    public ResponseEntity<CostumerResponseDTO> saveCostumer(@RequestBody CostumerSaveDTO costumerSaveDTO){
        return ResponseEntity.ok(toCostumerResponseDTOConverter.convert(saveCostumerUseCase.execute(costumerSaveDTO)));
    }

    @GetMapping("/{document}")
    public ResponseEntity<CostumerResponseDTO> getCostumer(@PathVariable String document){
        return ResponseEntity.ok(toCostumerResponseDTOConverter.convert(getCostumerUseCase.execute(document)));
    }

    @PutMapping
    public ResponseEntity<Void> updateCostumer(@RequestBody CostumerUpdateDTO costumerUpdateDTO){
        updateCostumerUseCase.execute(costumerUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{costumerId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable UUID costumerId){
        deleteCostumerUseCase.execute(costumerId);
        return ResponseEntity.noContent().build();
    }
}
