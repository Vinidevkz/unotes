package com.app.unotes.responsedtos;

import com.app.unotes.entities.Grade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record GradeResponseDTO(
        @NotBlank(message = "O nome_grade não pode estar vazio.") String nome_grade,
        @NotBlank(message = "O semestre_grade não pode estar vazio.") Integer semestre_grade,
        @NotBlank(message = "O ano_grade não pode estar vazio") Integer ano_grade
){
    public GradeResponseDTO(Grade grade) {
        this(grade.getNome_grade(), grade.getSemestre_grade(), grade.getAno_grade());
    }
}
