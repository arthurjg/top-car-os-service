package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.VeiculoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class VeiculoInputDB implements VeiculoInputPort {
	
	private final VeiculoRepository veiculoRepository;
	
	private final VeiculoDBMapper veiculoDBMapper;

	@Override
	public void save(Veiculo veiculo) {
		
		var veiculoDB = veiculoDBMapper.map(veiculo);		
		veiculoRepository.save(veiculoDB);
		
	}

	@Override
	public void delete(Veiculo veiculo) {
		veiculoRepository.delete(veiculoDBMapper.map(veiculo));		
	}

}
