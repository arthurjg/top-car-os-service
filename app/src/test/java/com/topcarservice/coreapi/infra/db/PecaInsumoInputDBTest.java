package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoRepository;

@ExtendWith(MockitoExtension.class)
class PecaInsumoInputDBTest {
	
	@InjectMocks
	PecaInsumoInputDB pecaInsumoInputDB;
	
	@Mock
	PecaInsumoRepository pecaInsumoRepository;
	
	@Mock
	PecaInsumoDBMapper pecaInsumoDBMapper;

	@Test
	void testSave() {
		
		PecaInsumo pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", "Ceramic", 50, new BigDecimal("150.00"));
		
		PecaInsumoDBEntity pecaInsumoEntityMock = PecaInsumoDBEntity.builder()
				.codigo(pecaInsumo.getCodigo())				
				.nome(pecaInsumo.getNome())
				.descricao(pecaInsumo.getDescricao())
				.fabricante(pecaInsumo.getFabricante())				
				.build();
		
		when(pecaInsumoDBMapper.map(pecaInsumo)).thenReturn(pecaInsumoEntityMock);
		
		pecaInsumoInputDB.save(pecaInsumo);
		
		verify(pecaInsumoRepository).save(pecaInsumoEntityMock);
	}

	@Test
	void testDelete() {
		
		PecaInsumo pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", "Ceramic", 50, new BigDecimal("150.00"));
		
		PecaInsumoDBEntity pecaInsumoEntityMock = PecaInsumoDBEntity.builder()
				.codigo(pecaInsumo.getCodigo())				
				.nome(pecaInsumo.getNome())
				.descricao(pecaInsumo.getDescricao())
				.fabricante(pecaInsumo.getFabricante())				
				.build();
		
		when(pecaInsumoDBMapper.map(pecaInsumo)).thenReturn(pecaInsumoEntityMock);
		
		pecaInsumoInputDB.delete(pecaInsumo);
		
		verify(pecaInsumoRepository).delete(pecaInsumoEntityMock);
	}

}
