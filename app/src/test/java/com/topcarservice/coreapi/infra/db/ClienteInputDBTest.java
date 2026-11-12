package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.ClienteDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteInputDBTest {
	
	@InjectMocks
	ClienteInputDB clienteInputDB;
	
	@Mock
	ClienteRepository clienteRepository;
	
	@Mock
	ClienteDBMapper clienteDBMapper;

	@Test
	void testSave() {
		
		Cliente cliente = new Cliente(1L, new CPF("051.972.259-96"), "John Doe");
		
		ClienteDBEntity clienteEntityMock = ClienteDBEntity.builder()
				.codigo(cliente.getCodigo())
				.cpf(cliente.getCpf().getUnformatted())
				.nome(cliente.getNome())
				.build();
		
		when(clienteDBMapper.map(cliente)).thenReturn(clienteEntityMock);
		
		clienteInputDB.save(cliente);
		
		verify(clienteRepository).save(clienteEntityMock);
	}

	@Test
	void testDelete() {
		
		Cliente cliente = new Cliente(1L, new CPF("051.972.259-96"), "John Doe");
		
		ClienteDBEntity clienteEntityMock = ClienteDBEntity.builder()
				.codigo(cliente.getCodigo())
				.cpf(cliente.getCpf().getUnformatted())
				.nome(cliente.getNome())
				.build();
		
		when(clienteDBMapper.map(cliente)).thenReturn(clienteEntityMock);
		
		clienteInputDB.delete(cliente);
		
		verify(clienteRepository).delete(clienteEntityMock);
	}

}
