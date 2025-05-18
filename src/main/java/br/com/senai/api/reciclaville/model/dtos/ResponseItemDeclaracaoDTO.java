package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

@Data
public class ResponseItemDeclaracaoDTO {

    private Long id;

    private Long materialId;

    private String materialNome;

    private Double toneladasDeclaradas;

    private Double percentualCompensacao;

    private Double toneladasCompensacao;
}
