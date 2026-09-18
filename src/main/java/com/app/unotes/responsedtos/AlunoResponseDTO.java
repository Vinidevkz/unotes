package com.app.unotes.responsedtos;

import com.app.unotes.entities.Aluno;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String nome_aluno,
        String sobrenome_aluno,
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "O email deve conter um formato válido (exemplo@dominio.com)")
        String email_aluno,
        LocalDate data_nascimento_aluno,
        String token
){
    public AlunoResponseDTO(Aluno aluno, String token) {
        this(aluno.getNome_aluno(), aluno.getSobrenome_aluno(), aluno.getEmail(), aluno.getData_nascimento_aluno(), token);
    }
}
