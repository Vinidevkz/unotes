package com.app.unotes.repository;

import com.app.unotes.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {

    Optional<List<Disciplina>> findAllByAlunoId(UUID id_aluno);

}
