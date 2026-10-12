package com.topcarservice.coreapi.domain.entities;

import java.util.stream.Stream;

public enum StatusOrdemServico {	
	
	RECEBIDA(1, "RECEBIDA"),
	EM_DIAGNOSTICO(2, "EM_DIAGNOSTICO"),
	AGUARDANDO_APROVACAO(3, "AGUARDANDO_APROVACAO"),
	EM_EXECUCAO(4, "EM_EXECUCAO"),
	FINALIZADA(5, "FINALIZADA"),
	ENTREGUE(6, "ENTREGUE");
	
	private int codigo;
	private String descricao;
	
	private StatusOrdemServico(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}	
	
	public static StatusOrdemServico fromDescricao(String descricao) {
		
		return Stream.of(StatusOrdemServico.values())		
			.filter(status -> status.getDescricao().equalsIgnoreCase(descricao))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Descrição de status inválida: " + descricao));		
	}
	
	public static StatusOrdemServico fromCodigo(int codigo) {
		
		return Stream.of(StatusOrdemServico.values())		
			.filter(status -> status.getCodigo() == codigo)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Código de status inválido: " + codigo));		
	}

}
