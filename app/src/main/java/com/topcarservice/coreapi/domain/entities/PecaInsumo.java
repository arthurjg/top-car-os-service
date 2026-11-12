package com.topcarservice.coreapi.domain.entities;

import java.math.BigDecimal;

public class PecaInsumo {
	
	private Long codigo;	
	
	private String nome;
	
	private String descricao;
	
	private String fabricante;
	
	private String modelo;
	
	private Integer quantidade;
	
	private BigDecimal valorUnitario;	
	
	private Servico servico;	

	@Deprecated
	public PecaInsumo() {
		super();		
	}
	
	public PecaInsumo(Long codigo, String nome, String descricao, String fabricante, String modelo,
			Integer quantidade, BigDecimal valorUnitario) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;		
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}	

}
