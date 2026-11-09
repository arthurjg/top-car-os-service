package com.topcarservice.coreapi.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topcarservice.coreapi.adapters.controllers.dto.ClienteDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ClienteMapper;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	ClienteUseCase clienteUseCase;
	
	@MockitoBean
	ClienteMapper clienteMapper;
	
	private ObjectMapper objectMapper;
	
	Cliente cliente;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		Long id = 1L;
		String nome = "Cliente Teste";
		cliente = new Cliente(id, nome, "12345678900");
	}

	@Test
	void testCadastrar() throws Exception {
		
		ClienteDTO param = ClienteDTO.builder()
				.nome("Cliente Teste")
				.cpf("12345678900")
				.build();					
		
		when(clienteMapper.map(any(ClienteDTO.class))).thenReturn(cliente);
		
		mockMvc.perform(
				post("/admin/clientes")					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isCreated());
	}

	@Test
	void testAtualizar() throws JsonProcessingException, Exception {
		
		ClienteDTO param = ClienteDTO.builder()
				.nome("Cliente Teste")
				.cpf("12345678900")
				.build();						
		
		when(clienteMapper.map(any(ClienteDTO.class))).thenReturn(cliente);
		
		mockMvc.perform(
				put("/admin/clientes/{id}", 1L)					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isNoContent());
	}

	@Test
	void testExcluir() throws JsonProcessingException, Exception {
		
		Long id = 1L;						
		
		when(clienteUseCase.carregar(id)).thenReturn(cliente);
		
		mockMvc.perform(
				delete("/admin/clientes/{id}", id)						
				)		
			.andExpect(status().isNoContent());
	}

}
