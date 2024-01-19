package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerResponseDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.CostumerSaveDTOFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.CostumerUpdateDTOFactory;
import com.challenge.backend.runthebank.usecase.costumer.DeleteCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.SaveCostumerUseCase;
import com.challenge.backend.runthebank.usecase.costumer.UpdateCostumerUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CostumerControllerTest {

    @Mock
    private final SaveCostumerUseCase saveCostumerUseCase = mock(SaveCostumerUseCase.class);

    @Mock
    private final GetCostumerUseCase getCostumerUseCase = mock(GetCostumerUseCase.class);

    @Mock
    private final UpdateCostumerUseCase updateCostumerUseCase = mock(UpdateCostumerUseCase.class);

    @Mock
    private final DeleteCostumerUseCase deleteCostumerUseCase = mock(DeleteCostumerUseCase.class);

    @Spy
    private final CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter = new CostumerToCostumerResponseDTOConverter();

    @InjectMocks
    private final CostumerController costumerController = new CostumerController(
            saveCostumerUseCase,
            getCostumerUseCase,
            updateCostumerUseCase,
            deleteCostumerUseCase,
            toCostumerResponseDTOConverter
    );

    @Test
    public void shouldSaveCostumer(){
        CostumerSaveDTO costumerSaveDTOMock = CostumerSaveDTOFactory.createCostumerSaveDTOPF();
        Costumer costumerMock = CostumerFactory.createCostumer();

        when(saveCostumerUseCase.execute(any(CostumerSaveDTO.class))).thenReturn(costumerMock);

        CostumerResponseDTO costumerResponseDTO = costumerController.saveCostumer(costumerSaveDTOMock).getBody();

        assert costumerResponseDTO != null;
        assertEquals(costumerMock.getCostumerId(), costumerResponseDTO.costumerId());
        verify(saveCostumerUseCase, atLeastOnce()).execute(any(CostumerSaveDTO.class));
    }

    @Test
    public void shouldGetCostumer(){
        Costumer costumerMock = CostumerFactory.createCostumer();

        when(getCostumerUseCase.execute(anyString())).thenReturn(costumerMock);

        CostumerResponseDTO costumerResponseDTO = costumerController.getCostumer(costumerMock.getDocument()).getBody();

        assert costumerResponseDTO != null;
        assertEquals(costumerMock.getCostumerId(), costumerResponseDTO.costumerId());
        verify(getCostumerUseCase, atLeastOnce()).execute(anyString());
    }

    @Test
    public void shouldUpdateCostumer(){
        CostumerUpdateDTO costumerUpdateDTOMock = CostumerUpdateDTOFactory.createCostumerUpdateDTOFactory("123.456.789-00", "", "Nova Rua Fulano", "");

        doNothing().when(updateCostumerUseCase).execute(any(CostumerUpdateDTO.class));

        costumerController.updateCostumer(costumerUpdateDTOMock);

        verify(updateCostumerUseCase, atLeastOnce()).execute(any(CostumerUpdateDTO.class));
    }

    @Test
    public void shouldDeleteCostumer(){
        UUID costumerUUIDMock = UUID.randomUUID();

        doNothing().when(deleteCostumerUseCase).execute(any(UUID.class));

        costumerController.deleteCostumer(costumerUUIDMock);

        verify(deleteCostumerUseCase, atLeastOnce()).execute(any(UUID.class));
    }
}
