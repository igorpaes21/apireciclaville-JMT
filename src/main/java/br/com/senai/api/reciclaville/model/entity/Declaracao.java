package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "declaracoes")
public class Declaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDate dataDeclaracao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente é obrigatório.")
    private Cliente cliente;

    @NotNull(message = "A data inicial é obrigatório.")
    @Column(nullable = false)
    private LocalDate dataInicialPeriodo;

    @NotNull(message = "A data final é obrigatório.")
    @Column(nullable = false)
    private LocalDate dataFinalPeriodo;

    @Column(nullable = false)
    private BigDecimal totalMateriais;

    @Column(nullable = false)
    private BigDecimal totalCompensado;

    @OneToMany(mappedBy = "declaracao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeclaracao> itens;

}
