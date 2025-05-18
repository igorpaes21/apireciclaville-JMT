package br.com.senai.api.reciclaville.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateMaterialDTO {


    @NotNull
    @Size(min = 3, max = 50, message = "O nome do material deve ter entre 3 e 50 caracteres.")
    private String nomeMaterial;

    @NotNull(message = "A percentagem de compensação é obrigatória.")
    @Min(value = 1, message = "A percentagem de compensação deve ser maior ou igual a 0.")
    private BigDecimal percentagemCompensacao;

    @NotNull(message = "A versão é obrigatória.")
    private Integer version;
}
