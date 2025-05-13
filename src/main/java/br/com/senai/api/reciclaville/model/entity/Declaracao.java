package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente;
    @Column(nullable = false)
    private LocalDate dataDeclaracao;
    @Column(nullable = false)
    private LocalDate dataInicialPeriodo;
    @Column(nullable = false)
    private LocalDate dataFinalPeriodo;
    @Column(nullable = false)
    private Double totalMateriais;
    @Column(nullable = false)
    private Double totalCompensado;
    @OneToMany(mappedBy = "declaracao", cascade = CascadeType.ALL)
    private List<ItemDeclaracao> itens;

}
