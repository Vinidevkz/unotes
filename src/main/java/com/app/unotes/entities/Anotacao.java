package com.app.unotes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_anotacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    @Size(max = 100, message = "O título da anotação deve ter no máximo 100 caracteres.")
    private String titulo_anotacao;
    @Size(max = 200, message = "A descrição da anotação deve ter no máximo 200 caracteres.")
    private String descricao_anotacao;
    private String texto_anotacao;

}
