package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.VeiculoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class VeiculoOutputDB implements VeiculoOutputPort {
	
	private final VeiculoRepository veiculoRepository;
	
	private final VeiculoDBMapper veiculoDBMapper;	

	@Override
	public Optional<Veiculo> findById(Long codigo) {		
		
		return veiculoRepository.findById(codigo)
				.map(veiculoDBMapper::mapTo);
	}

}

