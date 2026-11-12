package com.topcarservice.coreapi.bdd.ordemservico;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class OrdemServicoAtualizacaoSteps {
	
	@Autowired 
	private TestRestTemplate rest;
    
	@Autowired 
    private OrdemServicoRepository repository;
    
    private Long codigoOS;

    @Before
    public void limpar() {
        //repository.deleteAll();
    }

    @Dado("que existe uma Ordem de Serviço cadastrada com status \"Recebido\"")
    public void existeOSnoStatusRecebido() {
    	codigoOS = repository.findAll()
    			.stream()
    			.filter(os -> os.getStatus().equals(1))
    			.findFirst()
    			.orElseThrow()
    			.getCodigo();
        
    	assertThat(codigoOS).isNotNull();
    }

    @Quando("eu atualizo o status para \"Em_diagnostico\"")
    public void atualizarOrdemServico() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("codigoOS", codigoOS.toString());
        requestParams.put("status", "Em_diagnostico");
        rest.put("/admin/ordem-servicos/{codigoOS}", null, requestParams);        
        
    }

    @Então("a Ordem de Serviço deve estar na listagem com status {string}")
    public void osNaListagem(String status) {
    	OrdemServicoRetornoDTO os = rest.getForObject ("/public/ordem-servicos/{id}", OrdemServicoRetornoDTO.class, codigoOS);
        assertThat(os.getStatus()).isEqualTo(status);
    }

}
