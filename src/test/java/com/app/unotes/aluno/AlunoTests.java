package com.app.unotes.aluno;

import com.app.unotes.dtos.AlunoDTO;
import com.app.unotes.dtos.AlunoLoginDTO;
import com.app.unotes.dtos.AlunoUpdateDTO;
import com.app.unotes.entities.Aluno;
import com.app.unotes.responsedtos.AlunoDataResponseDTO;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import com.app.unotes.services.AlunoService;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AlunoTests {

    @Autowired
    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private AlunoService alunoService;

    //---------------------

    //gerar mocks
    private AlunoResponseDTO gerarAlunoResponseDTO(){
        Aluno aluno = new Aluno();
        aluno.setNome_aluno("Aluno Teste");

        AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO(aluno, "token123");

        return alunoResponseDTO;
    }

    private String gerarAlunoDTOJsonBody(){
        AlunoDTO alunoDTO = new AlunoDTO(
                "aluno_teste",
                "sobrenome_aluno_teste",
                "email@teste.com",
                "senha123",
                "01-01-01"
        );

        String jsonbody = objectMapper.writeValueAsString(alunoDTO);

        return jsonbody;
    }

    private String gerarAlunoLoginDTO(){
       AlunoLoginDTO alunoLoginDTO = new AlunoLoginDTO(
               "email@teste.com",
               "senha123"
       );

       String jsonbody = objectMapper.writeValueAsString(alunoLoginDTO);

       return jsonbody;
   }

   private AlunoDataResponseDTO gerarAlunoDataResponseDTO(){

        Aluno aluno = new Aluno();
        aluno.setNome_aluno("nome_teste");
        aluno.setSobrenome_aluno("sobrenome_teste");

        AlunoDataResponseDTO alunoDataResponseDTO = new AlunoDataResponseDTO(aluno);

        return alunoDataResponseDTO;
   }

   private String gerarAlunoUpdateDTO(){

       AlunoUpdateDTO alunoUpdateDTO = new AlunoUpdateDTO(
               "nome_teste",
               "sobrenome_teste",
               "ads_teste",
               "biografia_teste",
               "01-01-01"
       );

       String jsonbody = objectMapper.writeValueAsString(alunoUpdateDTO);

       return jsonbody;

   }

   //---------------------

    //cadastro
    @Test
    @DisplayName("Deve retornar 201 CREATED ao cadastrar um aluno.")
    void deveRetornar201CreatedAoCadastrarAluno() throws Exception{

        AlunoResponseDTO alunoResponseDTO = gerarAlunoResponseDTO();
        String jsonbody = gerarAlunoDTOJsonBody();

        when(alunoService.cadastroAluno(any(AlunoDTO.class))).thenReturn(alunoResponseDTO);

        mockMvc.perform(post("/v1/aluno/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonbody)
        )
                .andExpect(status().isCreated());


    }

    //login
    @Test
    @DisplayName("Deve retornar 200 OK ao realizar o login do aluno.")
    void deveRetornar200OkAoRealizarLoginDoAluno() throws Exception{

        AlunoResponseDTO alunoResponseDTO = gerarAlunoResponseDTO();
        String jsonbody = gerarAlunoLoginDTO();

        when(alunoService.loginAluno(any(AlunoLoginDTO.class))).thenReturn(alunoResponseDTO);

        mockMvc.perform(
                post("/v1/aluno/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonbody)
        )
                .andExpect(status().isOk());


    }

    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao buscar os dados do aluno.")
    void deveRetornar200OkAoBuscarOsDadosDoAluno() throws Exception {

        String nome_aluno = "nome_teste";
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AlunoDataResponseDTO responseDTO = gerarAlunoDataResponseDTO();

        when(alunoService.getAluno(nome_aluno, id)).thenReturn(responseDTO);

        mockMvc.perform(
                get("/v1/aluno/{nome_aluno}", nome_aluno)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    //update
    @Test
    @WithMockUser(username = "123e4567-e89b-12d3-a456-426614174000")
    @DisplayName("Deve retornar 200 OK ao atualizar um aluno.")
    void deveRetornar200OkAoAtualizarUmAluno() throws Exception {

        String alunoUpdateBody = gerarAlunoUpdateDTO();
        AlunoUpdateDTO alunoUpdateDTO = new AlunoUpdateDTO(
                "nome_teste",
                "sobrenome_teste",
                "bio_teste",
                "bio_teste",
                "01-01-01");
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AlunoDataResponseDTO alunoDataResponseDTO = gerarAlunoDataResponseDTO();

        when(alunoService.updateAluno(alunoUpdateDTO, id)).thenReturn(alunoDataResponseDTO);

        mockMvc.perform(
                put("/v1/aluno/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoUpdateBody)
        ).andExpect(status().isOk());


    }


    //put
    //delete
    //criar grade
    //criar disciplina
    //criar anotação
    //criar lembrete


}
