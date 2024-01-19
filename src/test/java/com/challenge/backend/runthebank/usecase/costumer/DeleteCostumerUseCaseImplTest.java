package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.impl.DeleteCostumerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class DeleteCostumerUseCaseImplTest {

    @Mock
    private final CostumerGateway costumerGateway = mock(CostumerGateway.class);

    @InjectMocks
    private final DeleteCostumerUseCaseImpl deleteCostumerUseCase = new DeleteCostumerUseCaseImpl(costumerGateway);

    @Test
    public void shouldDeleteCostumer(){

        deleteCostumerUseCase.execute(UUID.randomUUID());

        verify(costumerGateway, atLeastOnce()).deleteCostumer(any(UUID.class));
    }
}
