package com.topcarservice.coreapi.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.PecaInsumoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.PecaInsumoMapper;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Peça/Insumo", description = "Gerenciamento de peças e insumos")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/peca-insumos")
public class PecaInsumoController {
	
	PecaInsumoUseCase pecaInsumoUseCase;	
	
	PecaInsumoMapper pecaInsumoMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody @Validated PecaInsumoDTO pecaInsumoDto) throws Exception {		
		
		PecaInsumo pecaInsumo = pecaInsumoMapper.map(pecaInsumoDto);		
		
		pecaInsumoUseCase.cadastrar(pecaInsumo);		
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long codigo,
			@RequestBody @Validated PecaInsumoDTO pecaInsumoDto) {	
		
		PecaInsumo pecaInsumo = pecaInsumoMapper.map(pecaInsumoDto);		
		
		pecaInsumo.setCodigo(codigo);
		
		pecaInsumoUseCase.cadastrar(pecaInsumo);	
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long codigo) {	
		
		PecaInsumo pecaInsumo = pecaInsumoUseCase.carregar(codigo);
		pecaInsumoUseCase.excluir(pecaInsumo);	
		
		return ResponseEntity.noContent().build();
	}

}
