package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

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
class ServicoDBTest {
	
	@InjectMocks
	ServicoInputDB servicoInputInputDB;
	
	@Mock
	ServicoRepository servicoInputRepository;
	
	@Mock
	ServicoDBMapper servicoInputDBMapper;

	@Test
	void testSave() {
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		ServicoDBEntity servicoInputEntityMock = ServicoDBEntity.builder()
				.codigo(servico.getCodigo())
				.nome(servico.getNome())
				.build();
		
		when(servicoInputDBMapper.map(servico)).thenReturn(servicoInputEntityMock);
		
		servicoInputInputDB.save(servico);
		
		verify(servicoInputRepository).save(servicoInputEntityMock);
	}

	@Test
	void testDelete() {
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		ServicoDBEntity servicoInputEntityMock = ServicoDBEntity.builder()
				.codigo(servico.getCodigo())
				.nome(servico.getNome())
				.build();
		
		when(servicoInputDBMapper.map(servico)).thenReturn(servicoInputEntityMock);
		
		servicoInputInputDB.delete(servico);
		
		verify(servicoInputRepository).delete(servicoInputEntityMock);
	}

}
