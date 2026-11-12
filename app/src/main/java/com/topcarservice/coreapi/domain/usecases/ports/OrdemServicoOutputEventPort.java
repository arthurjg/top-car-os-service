package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.OrdemServico;

public interface OrdemServicoOutputEventPort {	

	void sendOSAtualizadaEvent(OrdemServico ordemServico);

}
