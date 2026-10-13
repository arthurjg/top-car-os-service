package com.topcarservice.coreapi.infra.db;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputDBPort;
import com.topcarservice.coreapi.infra.db.mappers.OrdemServicoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrdemServicoOutputDB implements OrdemServicoOutputDBPort {
	
	private final OrdemServicoRepository ordemServicoRepository;
	
	private final OrdemServicoDBMapper ordemServicoDBMapper;	

	@Override	
	public Optional<OrdemServico> findById(Long codigo) {		
		
		return ordemServicoRepository.findById(codigo)
				.map(ordemServicoDBMapper::mapToAggregate);
	}
	
	@Override	
	public Optional<OrdemServico> findAggregateById(Long codigo) {		
		
		return ordemServicoRepository.findById(codigo)
				.map(ordemServicoDBMapper::mapTo);
	}

	@Override
	public List<OrdemServico> findAll() {		
		return ordemServicoRepository.findAll()
				.stream()
				.map(ordemServicoDBMapper::mapTo)
				.toList();
	}

}

