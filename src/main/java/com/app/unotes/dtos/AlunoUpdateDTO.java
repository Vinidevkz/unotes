package com.app.unotes.dtos;

import java.time.LocalDate;

public record AlunoUpdateDTO(
    String nome_aluno,
    String sobrenome_aluno,
    String curso_aluno,
    String biografia_aluno,
    String data_nascimento_aluno
){}
