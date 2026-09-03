package com.app.unotes.services;

import com.app.unotes.configuration.TokenProvider;
import com.app.unotes.dtos.AlunoDTO;
import com.app.unotes.entities.Aluno;
import com.app.unotes.repository.AlunoRepository;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public AlunoResponseDTO cadastro_aluno(AlunoDTO alunoDTO){

        Aluno newAluno = new Aluno();
        newAluno.setNome_aluno(alunoDTO.nome_aluno());
        newAluno.setSobrenome_aluno(alunoDTO.sobrenome_aluno());
        newAluno.setEmail(alunoDTO.email_aluno());
        newAluno.setSenha_aluno(passwordEncoder.encode(alunoDTO.senha_aluno()));
        newAluno.setData_nascimento_aluno(LocalDate.parse(alunoDTO.data_nascimento()));

        Aluno aluno = alunoRepository.save(newAluno);
        String token = tokenProvider.buildToken(aluno.getId());

        AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO(aluno.getNome_aluno(), aluno.getSobrenome_aluno(), aluno.getEmail(), aluno.getData_nascimento_aluno(), token);

        return alunoResponseDTO;
    }

}
