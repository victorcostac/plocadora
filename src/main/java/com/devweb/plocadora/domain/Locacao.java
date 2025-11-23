package com.devweb.plocadora.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt_locacao", nullable = false, unique = true)
    private LocalDateTime dtLocacao;

    @Column(name = "dt_devolucao_prevista", nullable = false)
    private LocalDateTime dtDevolucaoPrevista;

    @Column(name = "dt_devolucao_efetiva", nullable = false)
    private LocalDateTime dtDevolucaoEfetiva;

    @Column(name = "valor_cobrado", nullable = false)
    private Double valorCobrado;

    @Column(name = "multa_cobrada", nullable = false)
    private Double multaCobrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Locacao(LocalDateTime dtLocacao, LocalDateTime dtDevolucaoPrevista, Double valorCobrado, Item item,
            Cliente cliente) {
        this.dtLocacao = dtLocacao;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.dtDevolucaoEfetiva = null;
        this.valorCobrado = valorCobrado;
        this.multaCobrada = null;
        this.item = item;
        this.cliente = cliente;
    }

    public void AtualizarLocacao(
            LocalDateTime dtDevolucaoEfetiva, Double multaCobrada) {
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
        this.multaCobrada = multaCobrada;
    }

    @Override
    public String toString() {
        return "Locacao [id=" + id + ", dtLocacao=" + dtLocacao + ", dtDevolucaoPrevista=" + dtDevolucaoPrevista
                + ", dtDevolucaoEfetiva=" + dtDevolucaoEfetiva + ", valorCobrado=" + valorCobrado + ", multaCobrada="
                + multaCobrada + ", item=" + item + ", cliente=" + cliente + "]";
    }

}
