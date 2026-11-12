package com.topcarservice.coreapi.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoItemSolicitadoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.OrdemServicoMapper;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

@WebMvcTest(OrdemServicoController.class)
class OrdemServicoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	OrdemServicoUseCase ordemServicoUseCase;
	
	@MockitoBean
	OrdemServicoMapper ordemServicoMapper;
	
	@MockitoBean
	ClienteUseCase clienteUseCase;
	
	@MockitoBean
	VeiculoUseCase veiculoUseCase;
	
	@MockitoBean
	ServicoUseCase servicoUseCase;
	
	@MockitoBean
	PecaInsumoUseCase pecaInsumoUseCase;
	
	private ObjectMapper objectMapper;
	
	OrdemServico ordemServico;
	OrdemServicoRetornoDTO param;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		Long id = 1L;		
		
		param = OrdemServicoRetornoDTO.builder()	
				.codigo(id)
				.build();
		
		ordemServico = new OrdemServico();
	}

	@Test
	void testCadastrar() throws Exception {					
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.cadastrar(any(OrdemServico.class))).thenReturn(ordemServico);
		
		mockMvc.perform(
				post("/admin/ordem-servicos")					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isCreated());
	}

	@Test
	void testAtualizarStatus() throws JsonProcessingException, Exception {						
		
		Long id = 1L;
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		mockMvc.perform(
				put("/admin/ordem-servicos/{id}", id)					
					.queryParam("status", "CONCLUIDA")
				)		
			.andExpect(status().isOk());
	}
	
	@Test
	void testFinalizarDiagnostico() throws JsonProcessingException, Exception {						
		
		Long id = 1L;
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		mockMvc.perform(
				put("/admin/ordem-servicos/{id}/diagnostico", id)
				)		
			.andExpect(status().isOk());
	}
	
	@Test
	void testAprovacao() throws JsonProcessingException, Exception {						
		
		Long id = 1L;
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		mockMvc.perform(
				put("/admin/ordem-servicos/{id}/aprovacao", id)
				)		
			.andExpect(status().isOk());
	}
	
	@Test
	void testiIncluirServico() throws JsonProcessingException, Exception {						
		
		Long id = 1L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/servicos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isOk());
	}
	
	@Test
	void testIncluirPecaInsumo() throws JsonProcessingException, Exception {						
		
		Long id = 1L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();
		
		when(ordemServicoMapper.map(any(OrdemServicoDTO.class))).thenReturn(ordemServico);
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/pecas-insumos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isOk());
	}
	
	@Test
	void testiIncluirServicoErroOSInexistente() throws JsonProcessingException, Exception {						
		
		Long id = 99L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();		
		
		when(ordemServicoUseCase.carregar(id)).thenThrow(new IllegalArgumentException("OrdemServico não encontrada"));
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/servicos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isNotFound());
	}
	
	@Test
	void testiIncluirPecaInsumoErroOSInexistente() throws JsonProcessingException, Exception {						
		
		Long id = 99L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();		
		
		when(ordemServicoUseCase.carregar(id)).thenThrow(new IllegalArgumentException("OrdemServico não encontrada"));
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/pecas-insumos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isNotFound());
	}
	
	@Test
	void testiIncluirServicoErroOSStatusInvalido() throws JsonProcessingException, Exception {						
		
		Long id = 99L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();		
		
		when(ordemServicoUseCase.carregar(id)).thenThrow(
				new IllegalStateException("Não é possível incluir peças/insumos em uma ordem de serviço apos o diagnostico."));
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/servicos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void testiIncluirPecaInsumoErroOSStatusInvalido() throws JsonProcessingException, Exception {						
		
		Long id = 99L;
		
		OrdemServicoItemSolicitadoDTO param = OrdemServicoItemSolicitadoDTO.builder()	
				.codigoItem(id)
				.quantidade(1)
				.build();		
		
		when(ordemServicoUseCase.carregar(id)).thenThrow(
				new IllegalStateException("Não é possível incluir peças/insumos em uma ordem de serviço apos o diagnostico."));
		
		mockMvc.perform(
				post("/admin/ordem-servicos/{id}/pecas-insumos", id)
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isBadRequest());
	}	

}
