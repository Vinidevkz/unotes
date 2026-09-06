package com.app.unotes.responsedtos;

import com.app.unotes.entities.Aluno;

import java.time.LocalDate;

public record AlunoDataResponseDTO(
        String nome_aluno,
        String sobrenome_aluno,
        String curso_aluno,
        String biografia_aluno,
        LocalDate data_nascimento_aluno

){
    public AlunoDataResponseDTO(Aluno aluno) {
        this(aluno.getNome_aluno(), aluno.getSobrenome_aluno(), aluno.getCurso_aluno(), aluno.getBiografia_aluno(), aluno.getData_nascimento_aluno());
    }



}
