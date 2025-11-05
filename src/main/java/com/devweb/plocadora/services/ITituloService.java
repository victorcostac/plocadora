package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Titulo;

import java.util.List;
import java.util.Optional;

public interface ITituloService {

    List<Titulo> getTitulos();

    Optional<Titulo> getTitulo(Long id);

    Titulo createTitulo(String nome, Integer ano, String sinopse, String categoria, Long diretorId, Long classeId,
            List<Long> atorIds);

    Optional<Titulo> updateTitulo(Long id, String nome, Integer ano, String sinopse, String categoria);

    Optional<Titulo> updateTituloWithRelations(Long id, String nome, Integer ano, String sinopse, String categoria,
            Long diretorId, Long classeId, List<Long> atorIds);

    boolean deleteTitulo(Long id);
}
