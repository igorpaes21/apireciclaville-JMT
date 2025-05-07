package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

@Data
public class ResponseClienteDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String atividadeEconomica;
    private String responsavel;

    public ResponseClienteDTO(Long id, String nome, String cnpj, String atividadeEconomica, String responsavel) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.atividadeEconomica = atividadeEconomica;
        this.responsavel = responsavel;
    }
}
