package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.OrdemServicoDBEntity;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoSolicitadoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoSolicitadoRepository;

@ExtendWith(MockitoExtension.class)
class PecaInsumoSolicitadoInputDBTest {
	
	@InjectMocks
	PecaInsumoSolicitadoInputDB pecaInsumoSolicitadoInputDB;
	
	@Mock
	PecaInsumoSolicitadoRepository pecaInsumoSolicitadoRepository;
	
	@Mock
	OrdemServicoRepository ordemServicoRepository;
	
	@Mock
	PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;

	@Test
	void testSave() {
		
		var pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", 
				"Ceramic", 50, new java.math.BigDecimal("150.00"));
		
		var pecaInsumoSolicitado = new PecaInsumoSolicitado(pecaInsumo, 2);
		
		PecaInsumoSolicitadoDBEntity pecaInsumoSolicitadoEntityMock = PecaInsumoSolicitadoDBEntity.builder()
				.codigo(pecaInsumoSolicitado.getCodigo())
				.nome(pecaInsumoSolicitado.getNome())
				.build();
		
		var ordemServico = new OrdemServico();
		
		when(ordemServicoRepository.findById(ordemServico.getCodigo())).thenReturn(Optional.of(new OrdemServicoDBEntity()));
		
		when(pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado)).thenReturn(pecaInsumoSolicitadoEntityMock);
		
		pecaInsumoSolicitadoInputDB.save(pecaInsumoSolicitado, ordemServico);
		
		verify(pecaInsumoSolicitadoRepository).save(pecaInsumoSolicitadoEntityMock);
	}

	@Test
	void testDelete() {
		
		var pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", 
				"Ceramic", 50, new java.math.BigDecimal("150.00"));
		
		var pecaInsumoSolicitado = new PecaInsumoSolicitado(pecaInsumo, 2);
		
		PecaInsumoSolicitadoDBEntity pecaInsumoSolicitadoEntityMock = PecaInsumoSolicitadoDBEntity.builder()
				.codigo(pecaInsumoSolicitado.getCodigo())
				.nome(pecaInsumoSolicitado.getNome())
				.build();
		
		when(pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado)).thenReturn(pecaInsumoSolicitadoEntityMock);
		
		pecaInsumoSolicitadoInputDB.delete(pecaInsumoSolicitado);
		
		verify(pecaInsumoSolicitadoRepository).delete(pecaInsumoSolicitadoEntityMock);
	}

}
