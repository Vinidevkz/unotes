package com.app.unotes.dtos;

import jakarta.validation.constraints.NotNull;

public record AlunoLoginDTO(
        @NotNull(message = "O campo email_aluno não pode estar vazio.") String email_aluno,
        @NotNull(message = "O campo senha_aluno não pode estar vazio.") String senha_aluno
){}
