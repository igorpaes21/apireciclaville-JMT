package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RequestDeclaracaoDTO {

    private Long clienteId;
    private LocalDate dataInicialPeriodo;
    private LocalDate dataFinalPeriodo;
    private List<RequestItemDeclaracaoDTO> itens;
}
