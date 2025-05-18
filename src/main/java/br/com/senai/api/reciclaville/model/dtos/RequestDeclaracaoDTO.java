package br.com.senai.api.reciclaville.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RequestDeclaracaoDTO {

    @NotNull(message = "O campo clienteId é obrigatório")
    private Long clienteId;

    @NotNull(message = "O campo data inicial periodo é obrigatório")
    private LocalDate dataInicialPeriodo;

    @NotNull(message = "O campo data final periodo é obrigatório")
    private LocalDate dataFinalPeriodo;

    private List<ItemDeclaracaoDTO> itens;
}
