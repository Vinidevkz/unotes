package com.app.unotes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_anotacoes")
@Getter
@Setter
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
    private String titulo_anotacao;
    private String descricao_anotacao;
    private String texto_anotacao;

}
