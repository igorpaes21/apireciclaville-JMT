package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ItemDeclaracao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    @NotNull(message = "O material é obrigatório.")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "declaracao_id", nullable = false)
    private Declaracao declaracao;

    @NotNull(message = "As toneladas declaradas são obrigatórias.")
    @Column(nullable = false)
    private BigDecimal toneladasDeclaradas;

    @Column(nullable = false)
    private BigDecimal percentualCompensacao;

    @Column(nullable = false)
    private BigDecimal toneladasCompensacao;

    @PrePersist
    @PreUpdate
    public void calcularCompensacao() {
        if (toneladasDeclaradas != null && percentualCompensacao != null) {
            toneladasCompensacao = toneladasDeclaradas
                    .multiply(percentualCompensacao)
                    .divide(BigDecimal.valueOf(100));
        }
    }


}
