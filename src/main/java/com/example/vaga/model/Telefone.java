package com.example.vaga.model;


import com.example.vaga.model.enums.TipoTelefon;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Telefone")
@ToString(exclude = "pessoa")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "TIPO_TELEFONE", nullable = false)
    private TipoTelefon tipoTelefone;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
