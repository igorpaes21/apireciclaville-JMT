package br.com.senai.api.reciclaville.repository;

import br.com.senai.api.reciclaville.model.entity.Material;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository <Material, Long> {
    Optional<Material> findByNomeMaterial(@NotNull(message = "O nome do material é obrigatório.") String nomeMaterial);
}
