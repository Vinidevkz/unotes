package com.app.unotes.repository;

import com.app.unotes.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {

    Optional<Grade> findByAlunoIdAndNomeGrade(UUID alunoId, String nomeGrade);

    //ver todas as grades de um aluno
    Optional<List<Grade>> findAllByAlunoId(UUID id_aluno);

    //pegar grade pelo id do aluno
}
