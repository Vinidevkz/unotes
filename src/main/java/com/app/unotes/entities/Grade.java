package com.app.unotes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_grades")
@Data
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
    @Size(max = 100, message = "O nome da grade deve ter no máximo 100 caracteres.")
    private String nome_grade;
    @Min(value = 1, message = "O semestre deve ser 1 o u 2")
    @Max(value = 2, message = "O semestre deve ser 1 ou 2")
    private Integer semestre_grade;
    private Integer ano_grade;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas = new ArrayList<>();

}
