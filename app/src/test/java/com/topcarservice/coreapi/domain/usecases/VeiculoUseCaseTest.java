package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoOutputPort;

@ExtendWith(MockitoExtension.class)
class VeiculoUseCaseTest {
	
	@InjectMocks
	VeiculoUseCase useCase;
	
	@Mock
	VeiculoInputPort inputPort;
	
	@Mock
	VeiculoOutputPort outputPort;
	
	Veiculo veiculo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		veiculo = new Veiculo(1L, "QHU-5R66", "Porche", "Cayenne", 2022);
			
	}

	@Test
	void testSalvar() {					
		
		useCase.cadastrar(veiculo);
		
		verify(inputPort).save(veiculo);
	}	

	@Test
	void testCarregar() {
		
		long codigo = 1L;		
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(veiculo));
		
		Veiculo veiculoResult = useCase.carregar(codigo);
		
		assertNotNull(veiculoResult);
		assertEquals(veiculo.getPlaca(), veiculoResult.getPlaca());
		assertEquals(veiculo.getMarca(), veiculoResult.getMarca());
		assertEquals(veiculo.getModelo(), veiculoResult.getModelo());
		assertEquals(veiculo.getAno(), veiculoResult.getAno());
	}

	@Test
	void testExcluir() {				
		
		useCase.excluir(veiculo);
		
		verify(inputPort).delete(veiculo);
	}

}
