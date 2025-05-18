package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

@Data
public class ResponseMaterialDTO {

    private Long id;
    private String nome;
    private Double percentagemCompensacao;
}
