package com.topcarservice.coreapi.domain.entities;

import java.math.BigDecimal;

public class ServicoSolicitado {
	
	private Long codigo;	
	
	private String nome;
	
	private BigDecimal valorMaoDeObra;	
	
	private Servico servico;
	
	@Deprecated
	public ServicoSolicitado() {
		super();		
	}
	
	public ServicoSolicitado(Servico servico) {
		super();
		this.servico = servico;
		this.nome = servico.getNome();
		this.valorMaoDeObra = servico.getValorMaoDeObra();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorMaoDeObra() {
		return valorMaoDeObra;
	}

	public void setValorMaoDeObra(BigDecimal valorMaoDeObra) {
		this.valorMaoDeObra = valorMaoDeObra;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}	

}
