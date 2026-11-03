package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class OrdemServicoInputDBTest {
	
	@InjectMocks
	OrdemServicoInputDB ordemServicoInputDB;
	
	@Mock
	OrdemServicoRepository ordemServicoRepository;
	
	@Mock
	OrdemServicoDBMapper ordemServicoDBMapper;

	@Test
	void testSave() {
		
		OrdemServico ordemServico = new OrdemServico();	
		
		OrdemServicoDBEntity ordemServicoEntityMock = OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.status(ordemServico.getStatus().ordinal())
				.build();
		
		when(ordemServicoDBMapper.map(ordemServico)).thenReturn(ordemServicoEntityMock);
		
		ordemServicoInputDB.save(ordemServico);
		
		verify(ordemServicoRepository).save(ordemServicoEntityMock);
	}

	@Test
	void testDelete() {
		
		OrdemServico ordemServico = new OrdemServico();	
		
		OrdemServicoDBEntity ordemServicoEntityMock = OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.status(ordemServico.getStatus().ordinal())
				.build();
		
		when(ordemServicoDBMapper.map(ordemServico)).thenReturn(ordemServicoEntityMock);
		
		ordemServicoInputDB.delete(ordemServico);
		
		verify(ordemServicoRepository).delete(ordemServicoEntityMock);
	}

}
