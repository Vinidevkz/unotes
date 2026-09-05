package com.app.unotes.responsedtos;

import com.app.unotes.entities.Aluno;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String nome_aluno,
        String sobrenome_aluno,
        String email_aluno,
        LocalDate data_nascimento_aluno,
        String token
){
    public AlunoResponseDTO(Aluno aluno, String token) {
        this(aluno.getNome_aluno(), aluno.getSobrenome_aluno(), aluno.getEmail(), aluno.getData_nascimento_aluno(), token);
    }
}
