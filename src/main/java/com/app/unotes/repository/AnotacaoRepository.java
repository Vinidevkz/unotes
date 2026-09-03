package com.app.unotes.repository;

import com.app.unotes.entities.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnotacaoRepository extends JpaRepository<Anotacao, UUID> {
}
