package com.topcarservice.coreapi.domain.usecases;

import java.util.List;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputDBPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputEventPort;


public class OrdemServicoUseCase {
	
	private final OrdemServicoInputPort inputPort;
	private final OrdemServicoOutputDBPort outputDBPort;
	private final OrdemServicoOutputEventPort outputEventPort;	
	private final ServicoSolicitadoUseCase servicoSolicitadoUseCase;	
	private final PecaInsumoSolicitadoUseCase pecaInsumoSolicitadoUseCase;	

	public OrdemServicoUseCase(OrdemServicoInputPort inputPort, OrdemServicoOutputDBPort outputDBPort,
			OrdemServicoOutputEventPort outputEventPort, ServicoSolicitadoUseCase servicoSolicitadoUseCase,
			PecaInsumoSolicitadoUseCase pecaInsumoSolicitadoUseCase) {
		super();
		this.inputPort = inputPort;
		this.outputDBPort = outputDBPort;
		this.outputEventPort = outputEventPort;
		this.servicoSolicitadoUseCase = servicoSolicitadoUseCase;
		this.pecaInsumoSolicitadoUseCase = pecaInsumoSolicitadoUseCase;
	}

	public OrdemServico cadastrar(OrdemServico ordemServico) {
		return inputPort.save(ordemServico);		
	}

	public OrdemServico carregar(Long codigo) {
		
		return outputDBPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não encontrada"));
	}

	public void excluir(OrdemServico ordemServico) {
		inputPort.delete(ordemServico);		
	}

	public List<OrdemServico> listar() {		
		return outputDBPort.findAll();
	}

	public void atualizar(OrdemServico ordemServico, String status) {		
		
		ordemServico.setStatus(StatusOrdemServico.fromDescricao(status));
		cadastrar(ordemServico);
		
		outputEventPort.sendOSAtualizadaEvent(ordemServico);
	}

	public OrdemServico incluirPecaInsumo(OrdemServico ordemServico, PecaInsumo pecaInsumo,
			Integer quantidade) {
		
		var pecaInsumoSolicitado = ordemServico.incluirPecasInsumos(pecaInsumo, quantidade);
		
		pecaInsumoSolicitadoUseCase.cadastrar(pecaInsumoSolicitado, ordemServico);		
		
		return ordemServico;
	}

	public OrdemServico incluirServico(OrdemServico ordemServico, Servico servico) {
		
		var servicoSolicitado = ordemServico.incluirServico(servico);
		
		servicoSolicitadoUseCase.cadastrar(servicoSolicitado, ordemServico);		
		
		return ordemServico;
	}

}
