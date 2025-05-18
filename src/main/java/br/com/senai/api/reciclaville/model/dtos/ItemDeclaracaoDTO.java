package br.com.senai.api.reciclaville.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDeclaracaoDTO {

    @NotNull(message = "O ID do material é obrigatório.")
    private Long materialId;

    @NotNull(message = "As toneladas declaradas são obrigatórias.")
    private BigDecimal toneladasDeclaradas;
}
