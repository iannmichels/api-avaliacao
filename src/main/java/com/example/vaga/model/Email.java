package com.example.vaga.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Email")
@ToString(exclude = "pessoa")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    private Pessoa pessoa;
}
