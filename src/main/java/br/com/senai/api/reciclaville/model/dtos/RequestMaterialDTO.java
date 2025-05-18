package br.com.senai.api.reciclaville.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestMaterialDTO {

    @NotNull
    @Size(min = 3, max = 50, message = "O nome do material deve ter entre 3 e 50 caracteres.")
    private String nomeMaterial;

    @NotNull(message = "A percentagem de compensação é obrigatória.")
    @Min(value = 1, message = "A percentagem de compensação deve ser maior ou igual a 0.")
    private Double percentagemCompensacao;

}
