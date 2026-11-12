package com.topcarservice.coreapi.infra.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoRepository;

@ExtendWith(MockitoExtension.class)
class PecaInsumoOutputDBTest {
	
	@InjectMocks
	PecaInsumoOutputDB pecaInsumoOutputDB;
	
	@Mock
	PecaInsumoRepository pecaInsumoRepository;
	
	@Mock
	PecaInsumoDBMapper pecaInsumoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		PecaInsumo pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", "Ceramic", 50, new BigDecimal("150.00"));
		
		PecaInsumoDBEntity pecaInsumoEntity = PecaInsumoDBEntity.builder()
				.codigo(pecaInsumo.getCodigo())				
				.nome(pecaInsumo.getNome())
				.descricao(pecaInsumo.getDescricao())
				.fabricante(pecaInsumo.getFabricante())				
				.build();		
		
		when(pecaInsumoRepository.findById(codigo)).thenReturn(Optional.of(pecaInsumoEntity));
		
		when(pecaInsumoDBMapper.mapTo(pecaInsumoEntity)).thenReturn(pecaInsumo);
		
		Optional<PecaInsumo> pecaInsumoResult = pecaInsumoOutputDB.findById(codigo);
		
		assertTrue(pecaInsumoResult.isPresent());
	}
	
	@Test
	void testFindAll() {
		
		PecaInsumo pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", "Ceramic", 50, new BigDecimal("150.00"));
		
		PecaInsumoDBEntity pecaInsumoEntity = PecaInsumoDBEntity.builder()
				.codigo(pecaInsumo.getCodigo())				
				.nome(pecaInsumo.getNome())
				.descricao(pecaInsumo.getDescricao())
				.fabricante(pecaInsumo.getFabricante())				
				.build();	
		
		when(pecaInsumoRepository.findAll()).thenReturn(List.of(pecaInsumoEntity));
		
		when(pecaInsumoDBMapper.mapTo(pecaInsumoEntity)).thenReturn(pecaInsumo);
		
		List<PecaInsumo> osResult = pecaInsumoOutputDB.findAll();
		
		assertNotNull(osResult);
		assertFalse(osResult.isEmpty());
	}

}
