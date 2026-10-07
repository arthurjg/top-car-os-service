package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;

public interface PecaInsumoSolicitadoInputPort {

	void save(PecaInsumoSolicitado pecaInsumoSolicitado);

	void delete(PecaInsumoSolicitado pecaInsumoSolicitado);

}
