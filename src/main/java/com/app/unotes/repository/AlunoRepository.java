package com.app.unotes.repository;

import com.app.unotes.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    Optional<Aluno> findByEmail(String email);

}