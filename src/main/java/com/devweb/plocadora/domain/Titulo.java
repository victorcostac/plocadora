package com.devweb.plocadora.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Titulo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "sinopse",length = 255)
    private String sinopse;

    @Column(name = "categoria", nullable = false, length = 70)
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id", nullable = false)
    private Diretor diretor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "titulo_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ator_id", nullable = false))
    private List<Ator> ator;
    
    @OneToMany(mappedBy = "titulo", fetch = FetchType.LAZY)
    private List<Item> itens;

}
