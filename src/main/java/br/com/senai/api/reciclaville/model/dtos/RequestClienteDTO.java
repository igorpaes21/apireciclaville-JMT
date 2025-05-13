package br.com.senai.api.reciclaville.model.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestClienteDTO {

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "O CNPJ não pode ser vazio")
    private String cnpj;
    @NotBlank(message = "A atividade econômica não pode ser vazia")
    private String atividadeEconomica;
    @NotBlank(message = "O responsável não pode ser vazio")
    private String responsavel;

}
