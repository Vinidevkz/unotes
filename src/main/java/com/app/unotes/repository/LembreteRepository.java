package com.app.unotes.repository;

import com.app.unotes.entities.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LembreteRepository extends JpaRepository<Lembrete, UUID> {
}
