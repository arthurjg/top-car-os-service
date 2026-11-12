package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;

public interface ServicoSolicitadoOutputPort {

	Optional<ServicoSolicitado> findById(Long codigo);

}
