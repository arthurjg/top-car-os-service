package com.topcarservice.coreapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

@DataJpaTest
class ClienteRepositoryITTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	ClienteDBEntity cliente;

	@BeforeEach
	void setUp() throws Exception {
		
		cliente = ClienteDBEntity.builder()				
				.cpf("123.456.789-00")
				.nome("John Doe")
				.build();
				
	}

	@Test
	void criaCliente_ComDadosValidos_RetornaCliente() {
		
		ClienteDBEntity clienteSalvo = clienteRepository.save(cliente);
		
		var clienteResultado = entityManager.find(ClienteDBEntity.class, clienteSalvo.getCodigo());
		
		assertNotNull(clienteResultado);
		assertEquals(clienteResultado.getNome(), cliente.getNome());
		assertEquals(clienteResultado.getCpf(), cliente.getCpf());		
	}
	
	@Test
	void carregaCliente_ComCodigoValido_RetornaCliente() {
		
		ClienteDBEntity clienteSalvo = entityManager.persist(cliente);		
		
		Optional<ClienteDBEntity> op = clienteRepository.findById(clienteSalvo.getCodigo());
		
		var clienteResultado = entityManager.find(ClienteDBEntity.class, clienteSalvo.getCodigo());
		
		assertTrue(op.isPresent());
		assertNotNull(clienteResultado);
		
	}
	
	@Test
	void atualizaCliente_ComCodigoValido_RetornaCliente() {
		
		ClienteDBEntity clienteSalvo = entityManager.persist(cliente);		
		
		clienteSalvo = entityManager.find(ClienteDBEntity.class, clienteSalvo.getCodigo());
		
		clienteSalvo.setNome("Joanna Doe");
		
		var clienteResultado = clienteRepository.save(clienteSalvo);
		
		assertNotNull(clienteResultado);
		assertEquals(clienteSalvo.getNome(), clienteResultado.getNome());
		
	}
	
	@Test
	void removeCliente_ComCodigoValido_RetornaVazio() {
		
		ClienteDBEntity clienteSalvo = entityManager.persist(cliente);		
		
		var clienteResultado = entityManager.find(ClienteDBEntity.class, clienteSalvo.getCodigo());
		
		clienteRepository.deleteById(clienteResultado.getCodigo());
		
		clienteResultado = entityManager.find(ClienteDBEntity.class, clienteResultado.getCodigo());		
		
		assertNull(clienteResultado);		
	}

}
