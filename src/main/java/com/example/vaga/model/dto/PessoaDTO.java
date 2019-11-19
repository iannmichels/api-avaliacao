package com.example.vaga.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String name;
    private String cpf;
    private List<TelefoneDTO> telefones;
    private List<EmailDTO> emails;
    private EnderecoDTO endereco;
    @Getter(AccessLevel.NONE)
    private String tasksFormat;

}
