package com.topcarservice.coreapi.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdemServico {
	
	private Long codigo;	
	
	private StatusOrdemServico status;		
		
	private Cliente cliente;
	
	private Veiculo veiculo;
	
	private List<ServicoSolicitado> servicos;	
	
	private List<PecaInsumoSolicitado> pecasInsumos;
	
	private LocalDateTime dataAbertura;

	public OrdemServico() {
		super();
		dataAbertura = LocalDateTime.now();
		status = StatusOrdemServico.RECEBIDA;		
		servicos = new ArrayList<>();		
		pecasInsumos = new ArrayList<>();
	}		

	public OrdemServico(Cliente cliente, Veiculo veiculo) {
		this();
		this.cliente = cliente;
		this.veiculo = veiculo;
	}

	@Deprecated
	public OrdemServico(Long codigo, StatusOrdemServico status, Cliente cliente, Veiculo veiculo,
			List<ServicoSolicitado> servicos, List<PecaInsumoSolicitado> pecasInsumos, LocalDateTime dataAbertura) {
		this(cliente, veiculo);
		this.codigo = codigo;
		this.status = status;		
		this.servicos.addAll(servicos);
		this.pecasInsumos.addAll(pecasInsumos);
		this.dataAbertura = dataAbertura;
	}	
	
	@Deprecated
	public OrdemServico(Long codigo, StatusOrdemServico status, Cliente cliente, Veiculo veiculo,
			LocalDateTime dataAbertura) {
		this(cliente, veiculo);
		this.codigo = codigo;
		this.status = status;		
		this.dataAbertura = dataAbertura;
	}	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ServicoSolicitado> getServicos() {
		return servicos;
	}	

	public List<PecaInsumoSolicitado> getPecasInsumos() {
		return pecasInsumos;
	}	

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public PecaInsumoSolicitado incluirPecasInsumos(PecaInsumo pecasInsumo, Integer quantidade) {
		if(this.pecasInsumos == null) {
			this.pecasInsumos = new ArrayList<>();
		}
		validarInclusaoItemOuServico();
		var pecaInsumoSolicitado = new PecaInsumoSolicitado(pecasInsumo, quantidade);
		this.pecasInsumos.add(new PecaInsumoSolicitado(pecasInsumo, quantidade));
		return pecaInsumoSolicitado;
	}

	public ServicoSolicitado incluirServico(Servico servico) {
		if(this.servicos == null) {
			this.servicos = new ArrayList<>();
		}
		validarInclusaoItemOuServico();
		var servicoSolicitado = new ServicoSolicitado(servico);
		this.servicos.add(servicoSolicitado);		
		return servicoSolicitado;
	}	
	
	private void validarInclusaoItemOuServico() {
		if( !(status == StatusOrdemServico.RECEBIDA || status == StatusOrdemServico.EM_DIAGNOSTICO) ) {
			throw new IllegalStateException("Não é possível incluir peças/insumos em uma ordem de serviço apos o diagnostico.");
		}
	}
	
	public void ePossivelFinalizarDiagnostico() {
		if( status != StatusOrdemServico.EM_DIAGNOSTICO || getServicos().isEmpty() || getPecasInsumos().isEmpty() ) {
			throw new IllegalStateException("Não é possível finalizar o diagnostico. "
					+ "Status da ordem de serviço deve ser EM_DIAGNOSTICO e deve conter pelo menos um serviço e uma peça/insumo.");
		}
	}

}
