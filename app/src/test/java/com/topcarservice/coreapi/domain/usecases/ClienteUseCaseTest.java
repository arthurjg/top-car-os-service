package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteOutputPort;

@ExtendWith(MockitoExtension.class)
class ClienteUseCaseTest {
	
	@InjectMocks
	ClienteUseCase useCase;
	
	@Mock
	ClienteInputPort inputPort;
	
	@Mock
	ClienteOutputPort outputPort;

	@Test
	void testCadastrar() {
		Cliente cliente = new Cliente(1L, "05197225996", "John Doe");		
		
		useCase.cadastrar(cliente);
		
		verify(inputPort).save(cliente);
	}
	
	@Test
	void testCarregar() {
		
		long codigo = 1L;
		
		Cliente cliente = new Cliente(1L, "05197225996", "John Doe");
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(cliente));
		
		Cliente clienteResult = useCase.carregar(codigo);
		
		assertNotNull(clienteResult);
		assertEquals(cliente.getNome(), clienteResult.getNome());
	}

}
