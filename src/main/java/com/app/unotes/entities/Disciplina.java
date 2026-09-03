package com.app.unotes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_disciplinas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_grade")
    private Grade grade;
    private String nome_disciplina;
    private String nome_professor;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Anotacao> anotacoes = new ArrayList<>();

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Lembrete> lembretes = new ArrayList<>();

}
