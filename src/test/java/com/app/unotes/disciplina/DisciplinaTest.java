package com.app.unotes.disciplina;

import com.app.unotes.dtos.DisciplinaDTO;
import com.app.unotes.entities.Grade;
import com.app.unotes.responsedtos.DisciplinaResponseDTO;
import com.app.unotes.services.DisciplinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplinaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DisciplinaService disciplinaService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    //metodos
    //-------

    public static DisciplinaDTO gerarDisciplinaDTO(){

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO("nome_teste", "nome_prof_teste", UUID.randomUUID());

        return disciplinaDTO;

    }

    public static DisciplinaResponseDTO gerarDisciplinaResponseDTO(){
        DisciplinaResponseDTO disciplinaResponseDTO = new DisciplinaResponseDTO("nome_teste", "nome_prof_teste", new Grade());

        return disciplinaResponseDTO;
    }

    //-------
    //

    //testes

    //criar disciplina
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 201 CREATED ao criar uma nova grade.")
    void deveRetornar201CreatedAoCriarUmaNovaGrade() throws Exception{

        DisciplinaDTO disciplinaDTO = gerarDisciplinaDTO();
        DisciplinaResponseDTO disciplinaResponseDTO = gerarDisciplinaResponseDTO();
        String jsonBody = objectMapper.writeValueAsString(disciplinaDTO);

        when(disciplinaService.criarDisciplina(disciplinaDTO)).thenReturn(disciplinaResponseDTO);

        mockMvc.perform(
                post("/v1/disciplinas/criar_disciplina")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isCreated());
    }

    //ver disciplina
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao buscar uma disciplina especifica.")
    void deveRetornar200OkAoBuscarUmaDisciplinaEspecifica() throws Exception{

        UUID id_grade = UUID.randomUUID();
        DisciplinaResponseDTO disciplinaResponseDTO = gerarDisciplinaResponseDTO();
        String jsonbody = objectMapper.writeValueAsString(disciplinaResponseDTO);

        when(disciplinaService.verDisciplina(id_grade)).thenReturn(disciplinaResponseDTO);

        mockMvc.perform(
                get("/v1/disciplinas/{id_grade}", id_grade)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonbody)
        ).andExpect(status().isOk());

    }

    //ver as disciplinas de um aluno
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao buscar todas as disciplinas de um aluno específico.")
    void deveRetornar200OkAoBuscarAsDisciplinasDeUmAluno() throws Exception{

        UUID id_aluno = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        List<DisciplinaResponseDTO> lista_teste = new ArrayList<>();

        String jsonBody = objectMapper.writeValueAsString(lista_teste);


        when(disciplinaService.verDisciplinasDeUmAluno(id_aluno)).thenReturn(lista_teste);

        mockMvc.perform(
                get("/v1/disciplinas/minhas_disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isOk());
    }

    //editar disciplina

    //deletar disciplina


}
