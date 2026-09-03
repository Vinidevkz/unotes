package com.app.unotes.dtos;

import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
        @NotNull(message = "O campo nome_aluno não pode estar vazio.") String nome_aluno,
        @NotNull(message = "O campo sobrenome_aluno não pode estar vazio.") String sobrenome_aluno,
        @NotNull(message = "O campo email_aluno não pode estar vazio.") String email_aluno,
        @NotNull(message = "O campo senha_aluno não pode estar vazio.") String senha_aluno,
        @NotNull(message = "O campo data_nascimento não pode estar vazio.") String data_nascimento
){}
