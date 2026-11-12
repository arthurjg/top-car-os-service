package com.topcarservice.coreapi.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

import jakarta.persistence.EntityManager;

@Disabled
@SpringBootTest
class ClienteRepositoryCompTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	ClienteDBEntity cliente;

	@BeforeEach
	void setUp() throws Exception {
		
		cliente = ClienteDBEntity.builder()				
				.cpf("123.456.789-00")
				.nome("John Doe")
				.build();
				
	}	
	
	@Disabled
	@Test
	void carregaCliente_ComCodigoValido_RetornaCliente() {				
		
		Optional<ClienteDBEntity> op = clienteRepository.findById(3L);		
		
		assertTrue(op.isPresent());		
		
	}	

}
