package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_inscricao", nullable = false, unique = true)
    private Long numInscricao;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "sexo", length = 20, nullable = false)
    private String sexo;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    protected Cliente(Long numInscricao, String nome, LocalDate dtNascimento, String sexo, Boolean ativo) {
        this.numInscricao = Objects.requireNonNull(numInscricao, "numInscricao não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "nome não pode ser nulo");
        this.dtNascimento = Objects.requireNonNull(dtNascimento, "dtNascimento não pode ser nulo");
        this.sexo = Objects.requireNonNull(sexo, "sexo não pode ser nulo");
        this.ativo = Objects.requireNonNull(ativo, "ativo não pode ser nulo");
    }

    public void atualizarDados(String nome, LocalDate dtNascimento, String sexo, Boolean ativo) {
        this.nome = Objects.requireNonNull(nome, "nome não pode ser nulo");
        this.dtNascimento = Objects.requireNonNull(dtNascimento, "dtNascimento não pode ser nulo");
        this.sexo = Objects.requireNonNull(sexo, "sexo não pode ser nulo");
        this.ativo = Objects.requireNonNull(ativo, "ativo não pode ser nulo");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", numInscricao=" + numInscricao +
                ", nome='" + nome + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", sexo='" + sexo + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
