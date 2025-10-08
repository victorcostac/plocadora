package com.devweb.plocadora.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classe")
public class Classe {

    // Getters e Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @Getter
    @Setter
    private String nome;

    @Column(name = "valor", nullable = false, length = 100)
    @Getter
    @Setter
    private Double valor;

    @Column(name = "prazoDevolucao", nullable = false, length = 100)
    @Getter
    @Setter
    private Double prazoDevolucao;


    // Construtores
    public Classe() {}

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