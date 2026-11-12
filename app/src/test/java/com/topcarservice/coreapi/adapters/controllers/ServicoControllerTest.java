package com.topcarservice.coreapi.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topcarservice.coreapi.adapters.controllers.dto.ServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ServicoMapper;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;

@WebMvcTest(ServicoController.class)
class ServicoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	ServicoUseCase servicoUseCase;
	
	@MockitoBean
	ServicoMapper servicoMapper;
	
	private ObjectMapper objectMapper;
	
	Servico servico;
	ServicoDTO servicoDTO;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		Long id = 1L;
		String nome = "troca pneus";
		BigDecimal valorMaoDeObra = new BigDecimal(150.0);
		long tempoExecucao = 60L;
		
		servicoDTO = ServicoDTO.builder()
				.nome(nome)
				.valorMaoDeObra(valorMaoDeObra)
				.tempoExecucaoPrevisto(tempoExecucao)
				.build();
		
		servico = new Servico(id, nome, valorMaoDeObra, tempoExecucao);
	}

	@Test
	void testCadastrar() throws Exception {			
		
		when(servicoMapper.map(any(ServicoDTO.class))).thenReturn(servico);
		
		mockMvc.perform(
				post("/admin/servicos")					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(servicoDTO))
				)		
			.andExpect(status().isCreated());
	}

	@Test
	void testAtualizar() throws JsonProcessingException, Exception {					
		
		when(servicoMapper.map(any(ServicoDTO.class))).thenReturn(servico);
		
		mockMvc.perform(
				put("/admin/servicos/{id}", 1L)					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(servicoDTO))
				)		
			.andExpect(status().isNoContent());
	}

	@Test
	void testExcluir() throws JsonProcessingException, Exception {
		
		Long id = 1L;						
		
		when(servicoUseCase.carregar(id)).thenReturn(servico);
		
		mockMvc.perform(
				delete("/admin/servicos/{id}", id)						
				)		
			.andExpect(status().isNoContent());
	}

}
