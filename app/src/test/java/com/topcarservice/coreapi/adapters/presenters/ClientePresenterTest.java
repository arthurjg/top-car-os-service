package com.topcarservice.coreapi.adapters.presenters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.topcarservice.coreapi.adapters.controllers.dto.ClienteDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ClienteMapper;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;

@WebMvcTest(ClientePresenter.class)
class ClientePresenterTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	ClienteUseCase clienteUseCase;
	
	@MockitoBean
	ClienteMapper clienteMapper;

	@Test
	void testBuscar() throws JsonProcessingException, Exception {
		
		Long id = 1L;
		String nome = "Cliente Teste";
		
		ClienteDTO clienteDTO = ClienteDTO.builder()
				.nome(nome)
				.cpf("12345678900")
				.build();
		
		Cliente cliente = new Cliente(id, nome, "12345678900");
		
		when(clienteUseCase.carregar(id)).thenReturn(cliente);
		
		when(clienteMapper.map(any(Cliente.class))).thenReturn(clienteDTO);
		
		mockMvc.perform(
				get("/admin/clientes/{id}", 1L)
                	.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.nome").value(nome));
	}

}
