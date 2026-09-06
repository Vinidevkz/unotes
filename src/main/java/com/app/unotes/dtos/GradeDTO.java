package com.app.unotes.dtos;

import jakarta.validation.constraints.NotBlank;

public record GradeDTO(
        @NotBlank(message = "O nome_grade não pode estar vazio.") String nome_grade,
        @NotBlank(message = "O semestre_grade não pode estar vazio.") Integer semestre_grade,
        @NotBlank(message = "O ano_grade não pode estar vazio") Integer ano_grade
){}
