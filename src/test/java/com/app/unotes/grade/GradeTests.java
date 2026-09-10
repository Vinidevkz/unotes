package com.app.unotes.grade;

import com.app.unotes.dtos.GradeDTO;
import com.app.unotes.entities.Grade;
import com.app.unotes.responsedtos.GradeResponseDTO;
import com.app.unotes.responsedtos.GradesDeUmAlunoResponseDTO;
import com.app.unotes.services.GradeService;
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
public class GradeTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GradeService gradeService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    //Métodos---------------------

    //-gerar GradeDTO
    public GradeDTO gerarGradeDTO(){
        GradeDTO gradeDTO = new GradeDTO("grade_teste", 2, 2026);

        return gradeDTO;
    }

    //-gerar GradeResponseDTO
    public GradeResponseDTO gerarGradeResponseDTO(){
        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO("grade_teste", 2, 2026);

        return gradeResponseDTO;
    }

    //-gerar GradesDeUmAlunoResponse
    public GradesDeUmAlunoResponseDTO gerarGradesDeUmAlunoResponseDTO(UUID alunoId){

        List<GradeResponseDTO> listaDeGrades = List.of(
                new GradeResponseDTO("Grade 2026.1", 1, 2026),
                new GradeResponseDTO( "Grade 2026.2", 2, 2026)
        );

        return new GradesDeUmAlunoResponseDTO(alunoId, listaDeGrades);

    }

    //---------------------

    //criar grade
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 201 CREATED ao criar uma nova grade.")
    void deveRetornar201CreatedAoCriarUmaNovaGrade() throws Exception {

        GradeDTO gradeDTO = gerarGradeDTO();
        GradeResponseDTO gradeResponseDTO = gerarGradeResponseDTO();

        UUID id_teste = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        String jsonBody = objectMapper.writeValueAsString(gradeDTO);

        when(gradeService.criarGrade(gradeDTO, id_teste)).thenReturn(gradeResponseDTO);

        mockMvc.perform(
                post("/v1/grades/criar_grade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isCreated());

    }

    //ver grade
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao ler uma grade.")
    void deveRetornar200OkAoLerUmaGrade() throws Exception {

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        GradeResponseDTO gradeResponseDTO = gerarGradeResponseDTO();
        String jsonBody =  objectMapper.writeValueAsString(gradeResponseDTO);
        String nome_grade = "faculdade";

        when(gradeService.getGrade(nome_grade, id)).thenReturn(gradeResponseDTO);

        mockMvc.perform(
                get("/v1/grades/{nome_grade}", nome_grade)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isOk());

    }

    //ver todas as grades de um aluno
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao pegar todas as grades de um aluno.")
    void deveRetornar200OkAoPegarTodasAsGradesDeUmAluno() throws Exception {

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        GradesDeUmAlunoResponseDTO gradesDeUmAlunoResponseDTO = gerarGradesDeUmAlunoResponseDTO(id);
        String jsonBody = objectMapper.writeValueAsString(gradesDeUmAlunoResponseDTO);

        when(gradeService.getGradesDeUmAluno(id)).thenReturn(gradesDeUmAlunoResponseDTO.grades());

        mockMvc.perform(
                get("/v1/grades/minhas_grades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isOk());


    }



}
