package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classe")
public class Classe {

    // Getters e Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @Setter
    private String nome;

    @Column(name = "valor", nullable = false, length = 100)
    @Getter
    private Double valor;

    @Column(name = "prazoDevolucao", nullable = false, length = 100)
    @Getter
    private Double prazoDevolucao;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Titulo> titulos;

    public Classe(String nome, Double valor, Double prazoDevolucao) {
        this.nome = nome;
        this.valor = valor;
        this.prazoDevolucao = prazoDevolucao;
    }

    // toString para debug
    @Override
    public String toString() {
        return "Ator{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                ", prazoDevolucao='" + prazoDevolucao + '\'' +
                '}';
    }
}