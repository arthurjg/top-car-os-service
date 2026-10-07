package com.topcarservice.coreapi.domain.entities;

import java.math.BigDecimal;

public class PecaInsumoSolicitado {
	
	private Long codigo;	
	
	private String nome;	
	
	private String modelo;
	
	private Integer quantidade;
	
	private BigDecimal valorUnitario;	
		
	private PecaInsumo pecaInsumo;
	
	@Deprecated
	public PecaInsumoSolicitado() {
		super();		
	}
	
	public PecaInsumoSolicitado(PecaInsumo pecaInsumo, Integer quantidade) {
		super();
		this.pecaInsumo = pecaInsumo;
		this.nome = pecaInsumo.getNome();
		this.modelo = pecaInsumo.getModelo();
		this.valorUnitario = pecaInsumo.getValorUnitario();
		this.quantidade = quantidade;
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

	public PecaInsumo getPecaInsumo() {
		return pecaInsumo;
	}

	public void setPecaInsumo(PecaInsumo pecaInsumo) {
		this.pecaInsumo = pecaInsumo;
	}	

}
