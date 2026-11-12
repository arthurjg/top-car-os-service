package com.topcarservice.coreapi.infra.db;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.ServicoSolicitadoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.ServicoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoSolicitadoRepository;

@ExtendWith(MockitoExtension.class)
class ServicoSolicitadoOutputDBTest {
	
	@InjectMocks
	ServicoSolicitadoOutputDB servicoSolicitadoOutputDB;
	
	@Mock
	ServicoSolicitadoRepository servicoSolicitadoRepository;
	
	@Mock
	ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		var servicoSolicitado = new ServicoSolicitado(servico);
		
		ServicoSolicitadoDBEntity servicoSolicitadoEntity = ServicoSolicitadoDBEntity.builder()
				.codigo(servicoSolicitado.getCodigo())
				.nome(servicoSolicitado.getNome())
				.build();
		
		when(servicoSolicitadoRepository.findById(codigo)).thenReturn(Optional.of(servicoSolicitadoEntity));
		
		when(servicoSolicitadoDBMapper.mapTo(servicoSolicitadoEntity)).thenReturn(servicoSolicitado);
		
		Optional<ServicoSolicitado> servicoSolicitadoResult = servicoSolicitadoOutputDB.findById(codigo);
		
		assertTrue(servicoSolicitadoResult.isPresent());
	}

}
