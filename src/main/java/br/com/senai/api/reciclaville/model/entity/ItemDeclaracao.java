package br.com.senai.api.reciclaville.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itens_declaracao")
public class ItemDeclaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_declaracao_id", nullable = false)
    private Declaracao declaracao;
    @ManyToOne
    @JoinColumn(name = "fk_material_id", nullable = false)
    Material material;
    @NotNull
    @Positive
    @Column(nullable = false)
    private Double toneladasDeclaradas;
    @NotNull
    @Positive
    @Column(nullable = false)
    private Double toneladasCompensacao;
    @NotNull
    @Positive
    @Column(nullable = false)
    private Double percentualCompensacao;
}
