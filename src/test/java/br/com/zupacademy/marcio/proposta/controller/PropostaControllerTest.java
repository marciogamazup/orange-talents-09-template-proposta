package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.dto.PropostaDto;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class PropostaControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarUmaNovaProposta() throws Exception {
        // ambiente
        PropostaDto propostaDto = new PropostaDto("35790039000134", "marcio.gama@zup.com.br", "Márcio Gama", "Rual Qualquer, 123", new BigDecimal(2500.0));
        String request = mapper.writeValueAsString(propostaDto);
        // comportamento
        MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.post("/propostas").contentType(MediaType.APPLICATION_JSON).content(request);

        // verificação do retorno

        mockMvc.perform(chamada)
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                ).andExpect(
                        MockMvcResultMatchers.redirectedUrlPattern("http://localhost/propostas/*")
                );

    }
}