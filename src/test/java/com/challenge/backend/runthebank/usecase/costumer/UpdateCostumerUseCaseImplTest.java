package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.CostumerUpdateDTOFactory;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.impl.UpdateCostumerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UpdateCostumerUseCaseImplTest {

    @Mock
    private final CostumerGateway costumerGateway = mock(CostumerGateway.class);

    @InjectMocks
    private final UpdateCostumerUseCaseImpl updateCostumerUseCase = new UpdateCostumerUseCaseImpl(costumerGateway);

    @Test
    public void shouldUpdateCostumerName(){
        CostumerUpdateDTO costumerUpdateDTOMock = CostumerUpdateDTOFactory.createCostumerUpdateDTOFactory("123.456.789-00","Novo Nome Costumer", "", "");
        Costumer costumerMock = CostumerFactory.createCostumer();

        when(costumerGateway.getCostumer(anyString())).thenReturn(costumerMock);

        updateCostumerUseCase.execute(costumerUpdateDTOMock);

        verify(costumerGateway, atLeastOnce()).getCostumer(anyString());
        verify(costumerGateway, atLeastOnce()).updateCostumer(any(CostumerUpdateDTO.class));
    }

    @Test public void shouldNotFoundCostumer(){
        CostumerUpdateDTO costumerUpdateDTOMock = CostumerUpdateDTOFactory.createCostumerUpdateDTOFactory("123.456.789-00","Novo Nome Costumer", "", "");

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> updateCostumerUseCase.execute(costumerUpdateDTOMock));

        assertEquals("Cliente não cadastrada.", exception.getStatusText());
        verify(costumerGateway, atLeastOnce()).getCostumer(anyString());
    }
}
