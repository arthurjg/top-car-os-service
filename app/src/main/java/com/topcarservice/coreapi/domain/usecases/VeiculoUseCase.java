package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoOutputPort;


public class VeiculoUseCase {
	
	private final VeiculoInputPort inputPort;
	private final VeiculoOutputPort outputPort;
	
	public VeiculoUseCase(VeiculoInputPort inputPort, VeiculoOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar(Veiculo veiculo) {
		inputPort.save(veiculo);		
	}

	public Veiculo carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado"));
	}

	public void excluir(Veiculo veiculo) {
		inputPort.delete(veiculo);		
	}

}
