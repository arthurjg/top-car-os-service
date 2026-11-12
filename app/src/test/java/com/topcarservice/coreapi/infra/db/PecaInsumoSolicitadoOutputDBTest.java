package com.topcarservice.coreapi.infra.db;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoSolicitadoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoSolicitadoRepository;

@ExtendWith(MockitoExtension.class)
class PecaInsumoSolicitadoOutputDBTest {
	
	@InjectMocks
	PecaInsumoSolicitadoOutputDB pecaInsumoSolicitadoOutputDB;
	
	@Mock
	PecaInsumoSolicitadoRepository pecaInsumoSolicitadoRepository;
	
	@Mock
	PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		var pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", 
				"Ceramic", 50, new java.math.BigDecimal("150.00"));
		
		var pecaInsumoSolicitado = new PecaInsumoSolicitado(pecaInsumo, 2);
		
		PecaInsumoSolicitadoDBEntity pecaInsumoSolicitadoEntity = PecaInsumoSolicitadoDBEntity.builder()
				.codigo(pecaInsumoSolicitado.getCodigo())
				.nome(pecaInsumoSolicitado.getNome())
				.build();
		
		when(pecaInsumoSolicitadoRepository.findById(codigo)).thenReturn(Optional.of(pecaInsumoSolicitadoEntity));
		
		when(pecaInsumoSolicitadoDBMapper.mapTo(pecaInsumoSolicitadoEntity)).thenReturn(pecaInsumoSolicitado);
		
		Optional<PecaInsumoSolicitado> pecaInsumoSolicitadoResult = pecaInsumoSolicitadoOutputDB.findById(codigo);
		
		assertTrue(pecaInsumoSolicitadoResult.isPresent());
	}

}
