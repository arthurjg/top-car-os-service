package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.OrdemServicoDBEntity;
import com.topcarservice.coreapi.infra.db.entities.ServicoSolicitadoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.ServicoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;
import com.topcarservice.coreapi.infra.db.repositories.ServicoSolicitadoRepository;

@ExtendWith(MockitoExtension.class)
class ServicoSolicitadoInputDBTest {
	
	@InjectMocks
	ServicoSolicitadoInputDB servicoSolicitadoInputDB;
	
	@Mock
	ServicoSolicitadoRepository servicoSolicitadoRepository;
	
	@Mock
	OrdemServicoRepository ordemServicoRepository;
	
	@Mock
	ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;

	@Test
	void testSave() {
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		var servicoSolicitado = new ServicoSolicitado(servico);
		
		ServicoSolicitadoDBEntity servicoSolicitadoEntityMock = ServicoSolicitadoDBEntity.builder()
				.codigo(servicoSolicitado.getCodigo())
				.nome(servicoSolicitado.getNome())
				.build();
		
		var ordemServico = new OrdemServico();		
		
		when(ordemServicoRepository.findById(ordemServico.getCodigo())).thenReturn(Optional.of(new OrdemServicoDBEntity()));
		
		when(servicoSolicitadoDBMapper.map(servicoSolicitado)).thenReturn(servicoSolicitadoEntityMock);
		
		servicoSolicitadoInputDB.save(servicoSolicitado, ordemServico);
		
		verify(servicoSolicitadoRepository).save(servicoSolicitadoEntityMock);
	}

	@Test
	void testDelete() {
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		var servicoSolicitado = new ServicoSolicitado(servico);
		
		ServicoSolicitadoDBEntity servicoSolicitadoEntityMock = ServicoSolicitadoDBEntity.builder()
				.codigo(servicoSolicitado.getCodigo())
				.nome(servicoSolicitado.getNome())
				.build();
		
		when(servicoSolicitadoDBMapper.map(servicoSolicitado)).thenReturn(servicoSolicitadoEntityMock);
		
		servicoSolicitadoInputDB.delete(servicoSolicitado);
		
		verify(servicoSolicitadoRepository).delete(servicoSolicitadoEntityMock);
	}

}
