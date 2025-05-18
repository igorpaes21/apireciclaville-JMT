package br.com.senai.api.reciclaville.model.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseDeclaracaoDTO {

    private Long id;

    private Long clienteId;

    private LocalDate dataDeclaracao;

    private LocalDate dataInicialPeriodo;

    private LocalDate dataFinalPeriodo;

    private Double totalMateriais;

    private Double totalCompensado;

    private List<ResponseItemDeclaracaoDTO> itens;
}
