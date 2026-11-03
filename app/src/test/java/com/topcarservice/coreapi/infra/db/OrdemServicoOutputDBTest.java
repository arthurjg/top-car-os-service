package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.infra.db.entities.OrdemServicoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.OrdemServicoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;

@ExtendWith(MockitoExtension.class)
class OrdemServicoOutputDBTest {
	
	@InjectMocks
	OrdemServicoOutputDB ordemServicoOutputDB;
	
	@Mock
	OrdemServicoRepository ordemServicoRepository;
	
	@Mock
	OrdemServicoDBMapper ordemServicoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		OrdemServico ordemServico = new OrdemServico();	
		
		OrdemServicoDBEntity ordemServicoEntity = OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.status(ordemServico.getStatus().ordinal())
				.build();
		
		when(ordemServicoRepository.findById(codigo)).thenReturn(Optional.of(ordemServicoEntity));
		
		when(ordemServicoDBMapper.mapTo(ordemServicoEntity)).thenReturn(ordemServico);
		
		Optional<OrdemServico> ordemServicoResult = ordemServicoOutputDB.findById(codigo);
		
		assertTrue(ordemServicoResult.isPresent());
	}
	
	@Test
	void testFindAggregateById() {
		
		long codigo = 1L;
		
		OrdemServico ordemServico = new OrdemServico();	
		
		OrdemServicoDBEntity ordemServicoEntity = OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.status(ordemServico.getStatus().ordinal())
				.build();
		
		when(ordemServicoRepository.findById(codigo)).thenReturn(Optional.of(ordemServicoEntity));
		
		when(ordemServicoDBMapper.mapToAggregate(ordemServicoEntity)).thenReturn(ordemServico);
		
		Optional<OrdemServico> ordemServicoResult = ordemServicoOutputDB.findAggregateById(codigo);
		
		assertTrue(ordemServicoResult.isPresent());
	}
	
	@Test
	void testFindAll() {
		
		OrdemServico ordemServico = new OrdemServico();	
		
		OrdemServicoDBEntity ordemServicoEntity = OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.status(ordemServico.getStatus().ordinal())
				.build();
		
		when(ordemServicoRepository.findAll()).thenReturn(List.of(ordemServicoEntity));
		
		when(ordemServicoDBMapper.mapTo(ordemServicoEntity)).thenReturn(ordemServico);
		
		List<OrdemServico> osResult = ordemServicoOutputDB.findAll();
		
		assertNotNull(osResult);
		assertFalse(osResult.isEmpty());
	}

}
