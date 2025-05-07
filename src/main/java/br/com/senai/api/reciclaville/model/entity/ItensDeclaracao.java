package br.com.senai.api.reciclaville.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itens_declaracao")
public class ItensDeclaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_declaracao_id", nullable = false)
    private Declaracao declaracao;
    @Column(nullable = false)
    private String material;
    @Column(nullable = false)
    private double percentualCompensacaoMaterial;
    @Column(nullable = false)
    private double toneladasDeclaradas;
    @Column(nullable = false)
    private double toneladasCompensadas;
}
