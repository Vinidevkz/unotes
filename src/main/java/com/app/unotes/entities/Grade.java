package com.app.unotes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Aluno id_aluno;
    private String nome_grade;
    private Integer semestre_grade;
    private Integer ano_grade;

    @OneToMany
    @JoinColumn(name = "id_grade")
    private List<Disciplina> disciplinas = new ArrayList<>();

}
