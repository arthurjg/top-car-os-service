package com.topcarservice.coreapi.domain.entities;

public class Veiculo {
	
	private Long codigo;
	
	private String placa;
	
	private String marca;
	
	private String modelo;
	
	private Integer ano;	

	@Deprecated
	public Veiculo() {
		super();		
	}

	public Veiculo(Long codigo, String placa, String marca, String modelo, Integer ano) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}	

}
