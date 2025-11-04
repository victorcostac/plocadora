package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "num_serie", nullable = false, unique = true)
    private Long numSerie;

    @Column(name = "dt_aquisicao", nullable = false)
    private LocalDateTime dtAquisicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoItem tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titulo_id", nullable = false)
    private Titulo titulo;

    public Item(Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Titulo titulo) {
        this.numSerie = numSerie;
        this.dtAquisicao = dtAquisicao;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public void update(Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Titulo titulo) {
        this.numSerie = numSerie;
        this.dtAquisicao = dtAquisicao;
        this.tipo = tipo;
        this.titulo = titulo;
    }
}
