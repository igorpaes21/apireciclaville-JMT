package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate dataInicial;
    @Column(nullable = false)
    private LocalDate dataFinal;
    @Column(nullable = false)
    private double totalCompensado;





}
