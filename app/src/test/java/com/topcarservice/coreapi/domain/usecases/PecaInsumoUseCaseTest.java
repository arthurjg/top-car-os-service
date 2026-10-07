package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoOutputPort;

@ExtendWith(MockitoExtension.class)
class PecaInsumoUseCaseTest {
	
	@InjectMocks
	PecaInsumoUseCase useCase;
	
	@Mock
	PecaInsumoInputPort inputPort;
	
	@Mock
	PecaInsumoOutputPort outputPort;
	
	PecaInsumo pecaInsumo;
	
	@BeforeEach
	void setUp() throws Exception {		
		pecaInsumo = new PecaInsumo(1L, "Pastilha de roda", "Pastilha de freio para veículos de passeio", "Brembo", "Ceramic", 50, new BigDecimal("150.00"));			
	}

	@Test
	void testCadastrar() {		 		
		
		useCase.cadastrar(pecaInsumo);
		
		verify(inputPort).save(pecaInsumo);
	}
	
	@Test
	void testCarregar() {
		
		long codigo = 1L;		
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(pecaInsumo));
		
		PecaInsumo pecaInsumoResult = useCase.carregar(codigo);
		
		assertNotNull(pecaInsumoResult);
		assertEquals(pecaInsumo.getNome(), pecaInsumoResult.getNome());
	}

}
