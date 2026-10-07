package com.topcarservice.coreapi.adapters.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.ClienteDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ClienteMapper;
import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cliente", description = "Gerenciamento de clientes")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/clientes")
public class ClientePresenter {
	
	private final ClienteUseCase clienteUseCase;
	
	private final ClienteMapper clienteMapper;	
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscar(@PathVariable("id") Long codigo) {		
				
		try {
			Cliente cliente = clienteUseCase.carregar(codigo);	
			ClienteDTO clienteDTO = clienteMapper.map(cliente);
			
			log.info("Cliente carregado com sucesso: {}", cliente.getCodigo());
			
			return ResponseEntity.ok().body(clienteDTO);
		} catch (IllegalArgumentException e) {			
			log.warn("Cliente não encontrado: {}", codigo);
			return ResponseEntity.notFound().build();
		}			
		
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ClienteDTO> buscarPeloCPF(@PathVariable String cpf) {		
				
		try {
			Cliente cliente = clienteUseCase.carregarPeloCPF(new CPF(cpf));	
			ClienteDTO clienteDTO = clienteMapper.map(cliente);
			
			log.info("Cliente carregado com sucesso: {}", cliente.getCodigo());
			
			return ResponseEntity.ok().body(clienteDTO);
		} catch (IllegalArgumentException e) {			
			log.warn("Cliente não encontrado: {}", cpf);
			return ResponseEntity.notFound().build();
		}					
	}

}
