package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materiais")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do material é obrigatório.")
    @Column(nullable = false)
    private String nomeMaterial;

    @NotNull(message = "A percentagem de compensação é obrigatória.")
    private BigDecimal percentagemCompensacao;

}
