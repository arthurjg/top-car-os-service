package com.topcarservice.coreapi.adapters.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.VeiculoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.VeiculoMapper;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Veículo", description = "Gerenciamento de veículos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/veiculos")
public class VeiculoPresenter {
	
	private final VeiculoUseCase veiculoUseCase;
	
	private final VeiculoMapper veiculoMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDTO> buscar(@PathVariable("id") Long codigo) {		
				
		try {
			Veiculo veiculo = veiculoUseCase.carregar(codigo);	
			VeiculoDTO veiculoDTO = veiculoMapper.mapTo(veiculo);
			
			return ResponseEntity.ok().body(veiculoDTO);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		}			
		
	}

}
