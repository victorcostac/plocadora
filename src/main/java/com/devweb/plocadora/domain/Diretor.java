package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "diretor")
public class Diretor {

    // Getters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @Getter
    private String nome;

    @OneToMany(mappedBy = "diretor", fetch = FetchType.LAZY)
    private List<Titulo> titulos;

    protected Diretor() {
        // Construtor padrão para JPA
    }

    public Diretor(String nome) {
        this.nome = nome;
    }

    public void atualizarNome(String nome) {
        this.nome = nome;
    }

    // toString para debug
    @Override
    public String toString() {
        return "Diretor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}