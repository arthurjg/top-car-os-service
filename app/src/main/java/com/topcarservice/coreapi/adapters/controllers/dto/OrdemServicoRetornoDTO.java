package com.topcarservice.coreapi.adapters.controllers.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoRetornoDTO {
	
	private Long codigo;
	private String status;
	private String cliente;
	private String veiculo;
	private List<OrdemServicoSolicitadoRetornoDTO> servicos;
	private List<OrdemServicoPecaInsumoSolicitadoDTO> pecasInsumos;
	private String dataAbertura;	

}
