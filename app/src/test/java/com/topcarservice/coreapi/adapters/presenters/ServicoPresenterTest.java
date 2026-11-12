package com.topcarservice.coreapi.adapters.presenters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.topcarservice.coreapi.adapters.controllers.dto.ServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ServicoMapper;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;

@WebMvcTest(ServicoPresenter.class)
class ServicoPresenterTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	ServicoUseCase servicoUseCase;
	
	@MockitoBean
	ServicoMapper servicoMapper;

	@Test
	void testBuscar() throws JsonProcessingException, Exception {
		
		Long id = 1L;
		String nome = "troca pneus";
		BigDecimal valorMaoDeObra = new BigDecimal(150.0);
		long tempoExecucao = 60L;
		
		ServicoDTO servicoDTO = ServicoDTO.builder()
				.nome(nome)
				.valorMaoDeObra(valorMaoDeObra)
				.tempoExecucaoPrevisto(tempoExecucao)
				.build();
		
		Servico servico = new Servico(1L, nome, valorMaoDeObra, tempoExecucao);
		
		when(servicoUseCase.carregar(id)).thenReturn(servico);
		
		when(servicoMapper.mapTo(any(Servico.class))).thenReturn(servicoDTO);
		
		mockMvc.perform(
				get("/admin/servicos/{id}", 1L)
                	.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.nome").value(nome));
	}

}
