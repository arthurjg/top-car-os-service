package com.topcarservice.coreapi.domain.entities;

public class Cliente {
	
	private Long codigo;
	
	private CPF cpf;
	
	private String cnpj;
	
	private String nome;		
	
	@Deprecated 
	public Cliente() { 
		super(); 
	}	 

	private Cliente(Long codigo, String nome) {
		this.codigo = codigo;		
		this.nome = nome;
	}
	
	public Cliente(Long codigo, CPF cpf, String nome) {
		this(codigo, nome);
		this.cpf = cpf;		
	}
	
	public Cliente(Long codigo, String cnpj, String nome) {
		this(codigo, nome);		
		this.cnpj = cnpj;		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public CPF getCpf() {
		return cpf;
	}
	
	public String getCnpj() {
		return cnpj;
	}	

	public String getNome() {
		return nome;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}		

}
