package com.topcarservice.coreapi.adapters.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.PecaInsumoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.PecaInsumoMapper;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Peça/Insumo", description = "Gerenciamento de peças e insumos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/peca-insumos")
public class PecaInsumoPresenter {
	
	private final PecaInsumoUseCase pecaInsumoUseCase;
	
	private final PecaInsumoMapper pecaInsumoMapper;	
	
	@GetMapping("/{id}")
	public ResponseEntity<PecaInsumoDTO> buscar(@PathVariable("id") Long codigo) {			
		
		try {
			PecaInsumo pecaInsumo = pecaInsumoUseCase.carregar(codigo);	
			
			PecaInsumoDTO pecaInsumoRetorno = pecaInsumoMapper.mapTo(pecaInsumo);
			
			return ResponseEntity.ok().body(pecaInsumoRetorno);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		}	
	}

}
