package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.infra.db.entities.VeiculoDBEntity;

@Component
public class VeiculoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public VeiculoDBEntity map(Veiculo veiculo) {		
		return objectMapper.map(veiculo, VeiculoDBEntity.class);
	}
	
	public Veiculo mapTo(VeiculoDBEntity veiculoDBEntity) {		
		return objectMapper.map(veiculoDBEntity, Veiculo.class);
	}

}
