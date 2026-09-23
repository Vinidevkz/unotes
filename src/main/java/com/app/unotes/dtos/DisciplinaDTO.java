package com.app.unotes.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DisciplinaDTO(
        @NotNull(message = "O nome da disciplina não pode estar vazio.") String nome_disciplina,
        @NotNull(message = "O nome do professor não pode estar vazio.") String nome_professor,
        @NotNull(message = "O id da grade não pode estar vazio") UUID id_grade

        ){}
