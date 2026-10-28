package com.topcarservice.coreapi.adapters.controllers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PagamentoDTO {
	
	private Long codigo;
	private Long codigoOrdemServico;
	private String formaPagamento;
	private String statusPagamento;
	private String identificadorPagador;
	private String identificadorRecebedor;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataPagamento;
	private BigDecimal valorTotal;

}
