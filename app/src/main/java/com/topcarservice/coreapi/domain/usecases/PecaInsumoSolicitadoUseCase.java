package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoOutputPort;


public class PecaInsumoSolicitadoUseCase {
	
	private final PecaInsumoSolicitadoInputPort inputPort;
	private final PecaInsumoSolicitadoOutputPort outputPort;
	
	public PecaInsumoSolicitadoUseCase(PecaInsumoSolicitadoInputPort inputPort, PecaInsumoSolicitadoOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar(PecaInsumoSolicitado pecaInsumoSolicitado) {
		inputPort.save(pecaInsumoSolicitado);		
	}

	public PecaInsumoSolicitado carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("PecaInsumoSolicitado não encontrado"));
	}

	public void excluir(PecaInsumoSolicitado pecaInsumoSolicitado) {
		inputPort.delete(pecaInsumoSolicitado);		
	}

}
