package com.topcarservice.coreapi.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoSolicitadoUseCase;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;
import com.topcarservice.coreapi.domain.usecases.ServicoSolicitadoUseCase;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoOutputPort;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.VeiculoOutputPort;

@Configuration
public class UseCasesConfig {
	
	@Bean
	ClienteUseCase clienteUseCase(ClienteInputPort inputPort, ClienteOutputPort outputPort) {
		return new ClienteUseCase(inputPort, outputPort);
	}
	
	@Bean
	OrdemServicoUseCase ordemServicoUseCase(OrdemServicoInputPort inputPort, OrdemServicoOutputPort outputPort) {
		return new OrdemServicoUseCase(inputPort, outputPort);
	}
	
	@Bean
	PecaInsumoSolicitadoUseCase pecaInsumoSolicitadoUseCase(PecaInsumoSolicitadoInputPort inputPort, PecaInsumoSolicitadoOutputPort outputPort) {
		return new PecaInsumoSolicitadoUseCase(inputPort, outputPort);
	}
	
	@Bean
	PecaInsumoUseCase pecaInsumoUseCase(PecaInsumoInputPort inputPort, PecaInsumoOutputPort outputPort) {
		return new PecaInsumoUseCase(inputPort, outputPort);
	}
	
	@Bean
	ServicoUseCase servicoUseCase(ServicoInputPort inputPort, ServicoOutputPort outputPort) {
		return new ServicoUseCase(inputPort, outputPort);
	}
	
	@Bean
	ServicoSolicitadoUseCase servicoSolicitadoUseCase(ServicoSolicitadoInputPort inputPort, ServicoSolicitadoOutputPort outputPort) {
		return new ServicoSolicitadoUseCase(inputPort, outputPort);
	}
	
	@Bean
	VeiculoUseCase veiculoUseCase(VeiculoInputPort inputPort, VeiculoOutputPort outputPort) {
		return new VeiculoUseCase(inputPort, outputPort);
	}

}
