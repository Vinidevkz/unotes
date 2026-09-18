package com.app.unotes.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AlunoLoginDTO(
        @NotNull(message = "O campo email_aluno não pode estar vazio.")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "O email deve conter um formato válido (exemplo@dominio.com)")
        String email_aluno,
        @NotNull(message = "O campo senha_aluno não pode estar vazio.") String senha_aluno
){}
