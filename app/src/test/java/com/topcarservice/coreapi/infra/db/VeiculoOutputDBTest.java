package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.infra.db.entities.VeiculoDBEntity;
import com.topcarservice.coreapi.infra.db.mappers.VeiculoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.VeiculoRepository;

@ExtendWith(MockitoExtension.class)
class VeiculoOutputDBTest {
	
	@InjectMocks
	VeiculoOutputDB veiculoOutputDB;
	
	@Mock
	VeiculoRepository veiculoRepository;
	
	@Mock
	VeiculoDBMapper veiculoDBMapper;

	@Test
	void testFindById() {
		
		long codigo = 1L;
		
		Veiculo veiculo = new Veiculo(1L, "QHU-5R66", "Porche", "Cayenne", 2022);
		
		VeiculoDBEntity veiculoEntity = VeiculoDBEntity.builder()
				.codigo(veiculo.getCodigo())
				.placa(veiculo.getPlaca())
				.marca(veiculo.getMarca())
				.modelo(veiculo.getModelo())
				.ano(veiculo.getAno())				
				.build();
		
		when(veiculoRepository.findById(codigo)).thenReturn(Optional.of(veiculoEntity));
		
		when(veiculoDBMapper.mapTo(veiculoEntity)).thenReturn(veiculo);
		
		Optional<Veiculo> veiculoResult = veiculoOutputDB.findById(codigo);
		
		assertTrue(veiculoResult.isPresent());
	}

}
