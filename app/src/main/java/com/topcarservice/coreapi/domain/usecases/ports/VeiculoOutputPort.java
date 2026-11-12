package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.Veiculo;

public interface VeiculoOutputPort {

	Optional<Veiculo> findById(Long codigo);

}
