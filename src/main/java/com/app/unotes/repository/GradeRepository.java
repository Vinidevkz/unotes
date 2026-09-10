package com.app.unotes.repository;

import com.app.unotes.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {

    //ver todas as grades de um aluno
    List<Grade> findByAlunoId(UUID alunoId);
}
