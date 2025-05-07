package br.com.senai.api.reciclaville.repository;

import br.com.senai.api.reciclaville.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
