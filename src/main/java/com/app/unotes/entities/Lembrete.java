package com.app.unotes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "id_lembrete")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    private LocalDateTime data_hora_prova;

}
