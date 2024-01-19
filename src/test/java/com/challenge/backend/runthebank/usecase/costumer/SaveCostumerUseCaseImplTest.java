package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.controller.converter.CostumerToCostumerResponseDTOConverter;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.enums.TipoPessoa;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.CostumerSaveDTOFactory;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.impl.SaveCostumerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveCostumerUseCaseImplTest {

    @Mock
    private final CostumerGateway costumerGateway = mock(CostumerGateway.class);

    @Spy
    private final CostumerToCostumerResponseDTOConverter toCostumerResponseDTOConverter = new CostumerToCostumerResponseDTOConverter();

    @InjectMocks
    private final SaveCostumerUseCaseImpl saveCostumerUseCase = new SaveCostumerUseCaseImpl(costumerGateway, toCostumerResponseDTOConverter);

    @Test
    public void shouldSaveCostumerPF(){
        CostumerSaveDTO costumerSaveDTOMock = CostumerSaveDTOFactory.createCostumerSaveDTOPF();
        UUID costumerToSaveUUID = UUID.fromString("fb20f00f-1421-4952-83d5-3c5200f1d557");
        Costumer costumerResponseMock = CostumerFactory.createCostumerFromCostumerSaveDTO(costumerToSaveUUID, costumerSaveDTOMock);

        when(costumerGateway.saveCostumer(any(Costumer.class))).thenReturn(costumerResponseMock);

        Costumer costumerResponse = saveCostumerUseCase.execute(costumerSaveDTOMock);

        assertEquals(costumerToSaveUUID, costumerResponse.getCostumerId());
        assertEquals(TipoPessoa.PF, costumerResponse.getTipoPessoa());
        verify(costumerGateway, atLeastOnce()).saveCostumer(any(Costumer.class));
    }

    @Test
    public void shouldSaveCostumerPJ(){
        CostumerSaveDTO costumerSaveDTOMock = CostumerSaveDTOFactory.createCostumerSaveDTOPJ();
        UUID costumerToSaveUUID = UUID.fromString("e8fe3e64-1b1b-45b7-a936-6b69f16f76f3");
        Costumer costumerResponseMock = CostumerFactory.createCostumerFromCostumerSaveDTO(costumerToSaveUUID, costumerSaveDTOMock);

        when(costumerGateway.saveCostumer(any(Costumer.class))).thenReturn(costumerResponseMock);

        Costumer costumerResponse = saveCostumerUseCase.execute(costumerSaveDTOMock);

        assertEquals(costumerToSaveUUID, costumerResponse.getCostumerId());
        assertEquals(TipoPessoa.PJ, costumerResponse.getTipoPessoa());
        verify(costumerGateway, atLeastOnce()).saveCostumer(any(Costumer.class));
    }

    @Test
    public void shouldExistsCostumer(){

        CostumerSaveDTO costumerSaveDTOMock = CostumerSaveDTOFactory.createCostumerSaveDTOPF();

        Costumer costumerExists = CostumerFactory.createCostumer();

        when(costumerGateway.getCostumer(anyString())).thenReturn(costumerExists);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> saveCostumerUseCase.execute(costumerSaveDTOMock));

        assertEquals("Já existe um cadastro para este documento.", exception.getStatusText());
        verify(costumerGateway, atLeastOnce()).getCostumer(anyString());
    }
}
