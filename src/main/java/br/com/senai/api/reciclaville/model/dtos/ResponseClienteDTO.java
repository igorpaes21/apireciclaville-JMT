package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

@Data
public class ResponseClienteDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String atividadeEconomica;
    private String responsavel;
}
