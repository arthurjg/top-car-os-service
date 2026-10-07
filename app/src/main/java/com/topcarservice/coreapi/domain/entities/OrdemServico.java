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
	public OrdemServico(Long codigo, StatusOrdemServico status, Cliente cliente,
			List<ServicoSolicitado> servicos, List<PecaInsumoSolicitado> pecasInsumos, LocalDateTime dataAbertura) {
		super();
		this.codigo = codigo;
		this.status = status;
		this.cliente = cliente;
		this.servicos = servicos;
		this.pecasInsumos = pecasInsumos;
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

	public void setServicos(List<ServicoSolicitado> servicos) {
		this.servicos = servicos;
	}

	public List<PecaInsumoSolicitado> getPecasInsumos() {
		return pecasInsumos;
	}

	public void setPecasInsumos(List<PecaInsumoSolicitado> pecasInsumos) {
		this.pecasInsumos = pecasInsumos;
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

}
