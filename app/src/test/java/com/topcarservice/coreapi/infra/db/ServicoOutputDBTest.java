package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.infra.db.entities.ServicoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.ServicoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoRepository;

@ExtendWith(MockitoExtension.class)
class ServicoOutputDBTest {
	
	@InjectMocks
	ServicoOutputDB servicoOutputDB;
	
	@Mock
	ServicoRepository servicoRepository;
	
	@Mock
	ServicoDBMapper servicoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		ServicoDBEntity servicoEntity = ServicoDBEntity.builder()
				.codigo(servico.getCodigo())
				.nome(servico.getNome())
				.build();
		
		when(servicoRepository.findById(codigo)).thenReturn(Optional.of(servicoEntity));
		
		when(servicoDBMapper.mapTo(servicoEntity)).thenReturn(servico);
		
		Optional<Servico> servicoResult = servicoOutputDB.findById(codigo);
		
		assertTrue(servicoResult.isPresent());
	}

}
