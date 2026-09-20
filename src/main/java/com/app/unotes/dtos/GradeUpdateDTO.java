package com.app.unotes.dtos;

import java.util.UUID;

public record GradeUpdateDTO(
    UUID id_grade,
    String nome_grade,
    Integer semestre_grade,
    Integer ano_grade
){}
