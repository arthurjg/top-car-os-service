package com.topcarservice.coreapi.infra.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class VeiculoInputDBTest {
	
	@InjectMocks
	VeiculoInputDB veiculoInputDB;
	
	@Mock
	VeiculoRepository veiculoRepository;
	
	@Mock
	VeiculoDBMapper veiculoDBMapper;

	@Test
	void testSave() {
		
		Veiculo veiculo = new Veiculo(1L, "QHU-5R66", "Porche", "Cayenne", 2022);
		
		VeiculoDBEntity veiculoEntityMock = VeiculoDBEntity.builder()
				.codigo(veiculo.getCodigo())
				.placa(veiculo.getPlaca())
				.marca(veiculo.getMarca())
				.modelo(veiculo.getModelo())
				.ano(veiculo.getAno())				
				.build();
		
		when(veiculoDBMapper.map(veiculo)).thenReturn(veiculoEntityMock);
		
		veiculoInputDB.save(veiculo);
		
		verify(veiculoRepository).save(veiculoEntityMock);
	}

	@Test
	void testDelete() {
		
		Veiculo veiculo = new Veiculo(1L, "QHU-5R66", "Porche", "Cayenne", 2022);
		
		VeiculoDBEntity veiculoEntityMock = VeiculoDBEntity.builder()
				.codigo(veiculo.getCodigo())
				.placa(veiculo.getPlaca())
				.marca(veiculo.getMarca())
				.modelo(veiculo.getModelo())
				.ano(veiculo.getAno())				
				.build();
		
		when(veiculoDBMapper.map(veiculo)).thenReturn(veiculoEntityMock);
		
		veiculoInputDB.delete(veiculo);
		
		verify(veiculoRepository).delete(veiculoEntityMock);
	}

}
