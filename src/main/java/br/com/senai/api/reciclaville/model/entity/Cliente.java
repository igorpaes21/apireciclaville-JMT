package br.com.senai.api.reciclaville.model.entity;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false)
    private String atividadeEconomica;
    @Column(nullable = false)
    private String responsavel;

//    public Collection<Object> getRoles() {
//        return null;
//    }
//
//    public String getPassword() {
//        return null;
//    }
//
//    public String getUsername() {
//        return null;
//    }
}
