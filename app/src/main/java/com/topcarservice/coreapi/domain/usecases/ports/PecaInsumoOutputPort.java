package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;

public interface PecaInsumoOutputPort {

	Optional<PecaInsumo> findById(Long codigo);

}
