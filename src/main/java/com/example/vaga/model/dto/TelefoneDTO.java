package com.example.vaga.model.dto;

import com.example.vaga.model.enums.TipoTelefon;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {
    private Long id;
    private String descricao;
    private TipoTelefon tipoTelefone;
}
