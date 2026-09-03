package com.app.unotes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome_aluno;
    private String sobrenome_aluno;
    @Column(unique = true)
    private String email;
    private String senha_aluno;
    private String biografia_aluno;
    private String curso_aluno;
    private LocalDate data_nascimento_aluno;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();

}
