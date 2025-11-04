package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "socio")
@Getter
@NoArgsConstructor
public class Socio extends Cliente {

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "endereco", length = 200, nullable = false)
    private String endereco;

    @Column(name = "tel", length = 15, nullable = false)
    private String tel;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Dependente> dependentes = new ArrayList<>();

    public Socio(Long numInscricao, String nome, LocalDate dtNascimento, String sexo, Boolean ativo,
                 String cpf, String endereco, String tel) {
        super(numInscricao, nome, dtNascimento, sexo, ativo);
        this.cpf = Objects.requireNonNull(cpf, "cpf não pode ser nulo");
        this.endereco = Objects.requireNonNull(endereco, "endereco não pode ser nulo");
        this.tel = Objects.requireNonNull(tel, "tel não pode ser nulo");
    }

    public void atualizarDadosSocio(String cpf, String endereco, String tel) {
        this.cpf = Objects.requireNonNull(cpf, "cpf não pode ser nulo");
        this.endereco = Objects.requireNonNull(endereco, "endereco não pode ser nulo");
        this.tel = Objects.requireNonNull(tel, "tel não pode ser nulo");
    }

    public void adicionarDependente(Dependente dependente) {
        dependentes.add(dependente);
        dependente.setSocio(this);
    }

    public void removerDependente(Dependente dependente) {
        dependentes.remove(dependente);
        dependente.setSocio(null);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + getId() +
                ", numInscricao=" + getNumInscricao() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tel='" + tel + '\'' +
                ", totalDependentes=" + dependentes.size() +
                '}';
    }
}
