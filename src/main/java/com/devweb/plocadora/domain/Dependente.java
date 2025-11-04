package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "dependente")
@Getter
@NoArgsConstructor
public class Dependente extends Cliente {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    public Dependente(Long numInscricao, String nome, LocalDate dtNascimento, String sexo, Boolean ativo, Socio socio) {
        super(numInscricao, nome, dtNascimento, sexo, ativo);
        this.socio = Objects.requireNonNull(socio, "Sócio não pode ser nulo");
    }

    // Método package-private para ser usado apenas pelo Socio
    void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void atualizarSocio(Socio socio) {
        this.socio = Objects.requireNonNull(socio, "Sócio não pode ser nulo");
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + getId() +
                ", numInscricao=" + getNumInscricao() +
                ", nome='" + getNome() + '\'' +
                ", socioId=" + (socio != null ? socio.getId() : "null") +
                ", socioNome='" + (socio != null ? socio.getNome() : "null") + '\'' +
                '}';
    }
}
