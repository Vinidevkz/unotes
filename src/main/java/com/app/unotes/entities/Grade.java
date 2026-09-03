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
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    private String nome_grade;
    private Integer semestre_grade;
    private Integer ano_grade;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas = new ArrayList<>();

}
