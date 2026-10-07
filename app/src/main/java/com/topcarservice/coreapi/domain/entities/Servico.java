package com.topcarservice.coreapi.domain.entities;

import java.math.BigDecimal;
import java.util.List;

public class Servico {
	
	private Long codigo;	
	
	private String nome;
	
	private BigDecimal valorMaoDeObra;	
	
	private List<PecaInsumo> pecasInsumos;
	
	@Deprecated
	public Servico() {
		super();		
	}
	
	public Servico(Long codigo, String nome, BigDecimal valorMaoDeObra) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valorMaoDeObra = valorMaoDeObra;		
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

	public List<PecaInsumo> getPecasInsumos() {
		return pecasInsumos;
	}

	public void setPecasInsumos(List<PecaInsumo> pecasInsumos) {
		this.pecasInsumos = pecasInsumos;
	}	

}
