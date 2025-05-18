package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do cliente é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O CNPJ do cliente é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @NotNull(message = "A atividade econômica é obrigatório.")
    @Column(nullable = false)
    private String atividadeEconomica;

    @NotNull(message = "O nome do responsável é obrigatório.")
    @Column(nullable = false)
    private String responsavel;

}
