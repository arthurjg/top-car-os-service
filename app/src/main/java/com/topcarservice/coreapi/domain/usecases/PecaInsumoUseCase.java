package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities. PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.ports. PecaInsumoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports. PecaInsumoOutputPort;


public class PecaInsumoUseCase {
	
	private final  PecaInsumoInputPort inputPort;
	private final  PecaInsumoOutputPort outputPort;
	
	public PecaInsumoUseCase( PecaInsumoInputPort inputPort,  PecaInsumoOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar( PecaInsumo  pecaInsumo) {
		inputPort.save( pecaInsumo);		
	}

	public  PecaInsumo carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException(" PecaInsumo não encontrado"));
	}

	public void excluir( PecaInsumo  pecaInsumo) {
		inputPort.delete( pecaInsumo);		
	}

}
