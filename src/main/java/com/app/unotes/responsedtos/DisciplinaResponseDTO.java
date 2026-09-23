package com.app.unotes.responsedtos;

import com.app.unotes.entities.Disciplina;
import com.app.unotes.entities.Grade;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DisciplinaResponseDTO(
    @NotNull(message = "O nome da disiplina não pode estar vazio.") String nome_disiplina,
    @NotNull(message = "O nome do professor não pode estar vazio.") String nome_professor,
    @NotNull(message = "O id da grade não pode estar vazio") Grade grade
){
    public DisciplinaResponseDTO(Disciplina disciplina){
        this(disciplina.getNome_disciplina(), disciplina.getNome_professor(), disciplina.getGrade());
    }
}
