package com.app.unotes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
    private String nome_aluno;
    @Size(max = 50, message = "O sobrenome deve ter no máximo 50 caracteres.")
    private String sobrenome_aluno;
    @Column(unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "O email deve conter um formato válido (exemplo@dominio.com)")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres.")
    private String email;
    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres.")
    private String senha_aluno;
    @Size(max = 200, message = "A biografia deve ter no máximo 200 caracteres.")
    private String biografia_aluno;
    @Size(max = 50, message = "O nome do curso deve ter no máximo 50 caracteres.")
    private String curso_aluno;
    private LocalDate data_nascimento_aluno;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();

}
