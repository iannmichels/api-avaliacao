package com.example.vaga.model;


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
@Table(name = "Endereco")
@ToString(exclude = "pessoa")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "LOGRADOURO", nullable = false)
    private String logradouro;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "UF", nullable = false)
    private String uf;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @OneToOne(optional = false)
    @JoinColumn(name = "PESSOA_ID", nullable = false)
    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco end = (Endereco) o;
        return Objects.equals(id, end.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
