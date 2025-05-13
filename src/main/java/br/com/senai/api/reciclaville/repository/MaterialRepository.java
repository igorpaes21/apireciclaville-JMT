package br.com.senai.api.reciclaville.repository;

import br.com.senai.api.reciclaville.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository <Material, Long> {
}
