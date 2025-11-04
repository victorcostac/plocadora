package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Ator;

import java.util.List;
import java.util.Optional;

public interface IAtorService {
    List<Ator> getAtores();
    Optional<Ator> getAtor(Long id);
    Ator createAtor(String nome);
    Optional<Ator> updateAtor(Long id, String nome);
    boolean deleteAtor(Long id);
}

