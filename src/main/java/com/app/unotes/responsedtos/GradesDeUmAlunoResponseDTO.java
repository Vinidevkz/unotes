package com.app.unotes.responsedtos;

import java.util.List;
import java.util.UUID;

public record GradesDeUmAlunoResponseDTO(
    UUID id_aluno,
    List<GradeResponseDTO> grades
){}
