package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.VeiculoDTO;
import com.topcarservice.coreapi.domain.entities.Veiculo;

@Component
public class VeiculoMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public Veiculo map(VeiculoDTO veiculoDto) {		
		return new Veiculo(null, veiculoDto.getPlaca(), veiculoDto.getMarca(), veiculoDto.getModelo(), veiculoDto.getAno());		
	}
	
	
	//TODO: Verificar se é necessário mapear o cliente para evitar o problema de referência circular
	public VeiculoDTO mapTo(Veiculo veiculo) {		
		return VeiculoDTO.builder()
				.placa(veiculo.getPlaca())
				.marca(veiculo.getMarca())
				.modelo(veiculo.getModelo())
				.ano(veiculo.getAno())
				//.codigoCliente(veiculo.getCliente().getCodigo())
				.build();
	}

}
