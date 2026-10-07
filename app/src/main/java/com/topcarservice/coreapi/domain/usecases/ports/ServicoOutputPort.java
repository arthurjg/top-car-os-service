package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.Servico;

public interface ServicoOutputPort {

	Optional<Servico> findById(Long codigo);

}
