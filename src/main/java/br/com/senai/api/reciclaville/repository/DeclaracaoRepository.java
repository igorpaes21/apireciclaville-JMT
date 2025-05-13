package br.com.senai.api.reciclaville.repository;

import br.com.senai.api.reciclaville.model.entity.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclaracaoRepository extends JpaRepository<Declaracao, Long> {

}
