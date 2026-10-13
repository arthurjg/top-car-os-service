package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.List;
import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.OrdemServico;

public interface OrdemServicoOutputDBPort {

	Optional<OrdemServico> findById(Long codigo);
	
	Optional<OrdemServico> findAggregateById(Long codigo);

	List<OrdemServico> findAll();

}
