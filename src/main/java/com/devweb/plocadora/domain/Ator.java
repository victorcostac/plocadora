package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ator")
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @Getter
    private String nome;

    @ManyToMany(mappedBy = "atores", fetch = FetchType.LAZY)
    private List<Titulo> titulos;

    protected Ator() {
        // Construtor padrão para JPA
    }

    public Ator(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome do ator não pode ser nulo");
    }

    public void atualizarNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome do ator não pode ser nulo");
    }

    // toString para debug
    @Override
    public String toString() {
        return "Ator{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}