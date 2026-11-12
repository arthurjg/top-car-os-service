package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.ClienteDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteOutputDBTest {
	
	@InjectMocks
	ClienteOutputDB clienteOutputDB;
	
	@Mock
	ClienteRepository clienteRepository;
	
	@Mock
	ClienteDBMapper clienteDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		var clienteEntity = ClienteDBEntity.builder()
				.codigo(codigo)
				.cpf("05197225996")
				.nome("John Doe")
				.build();
		
		Cliente cliente = new Cliente(codigo, "05197225996", "John Doe");
		
		when(clienteRepository.findById(codigo)).thenReturn(Optional.of(clienteEntity));
		
		when(clienteDBMapper.mapTo(clienteEntity)).thenReturn(cliente);
		
		Optional<Cliente> clienteResult = clienteOutputDB.findById(codigo);
		
		assertTrue(clienteResult.isPresent());
	}

}
