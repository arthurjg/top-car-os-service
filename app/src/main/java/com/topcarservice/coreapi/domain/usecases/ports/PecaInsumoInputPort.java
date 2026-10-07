package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;

public interface PecaInsumoInputPort {

	void save(PecaInsumo pecaInsumo);

	void delete(PecaInsumo pecaInsumo);

}
