package com.app.unotes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_disciplinas")
@Data
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
    @NotNull(message = "O nome da disciplina não pode estar vazio.")
    @Size(max = 50, message = "O nome da disciplina deve ter no máximo 50 caracteres.")
    private String nome_disciplina;
    @NotNull(message = "O nome do professor não pode estar vazio.")
    @Size(max = 100, message = "O nome do professor deve ter no máximo 100 caracteres.")
    private String nome_professor;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Anotacao> anotacoes = new ArrayList<>();

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Lembrete> lembretes = new ArrayList<>();

}
