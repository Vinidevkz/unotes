package com.app.unotes.responsedtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String nome_aluno,
        String sobrenome_aluno,
        String email_aluno,
        LocalDate data_nascimento_aluno,
        String token
){
    public AlunoResponseDTO(String nome_aluno, String sobrenome_aluno, String email_aluno, LocalDate data_nascimento_aluno, String token) {
        this.nome_aluno = nome_aluno;
        this.sobrenome_aluno = sobrenome_aluno;
        this.email_aluno = email_aluno;
        this.data_nascimento_aluno = data_nascimento_aluno;
        this.token = token;

    }
}
