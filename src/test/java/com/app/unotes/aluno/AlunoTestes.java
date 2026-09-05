package com.app.unotes.aluno;

import com.app.unotes.dtos.AlunoDTO;
import com.app.unotes.dtos.AlunoLoginDTO;
import com.app.unotes.entities.Aluno;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import com.app.unotes.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AlunoTestes {

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

    //update
    //put
    //delete
    //criar grade
    //criar disciplina
    //criar anotação
    //criar lembrete


}
