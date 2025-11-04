package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Diretor;

import java.util.List;
import java.util.Optional;

public interface IDiretorService {
    List<Diretor> getDiretores();
    Optional<Diretor> getDiretor(Long id);
    Diretor createDiretor(String nome);
    Optional<Diretor> updateDiretor(Long id, String nome);
    boolean deleteDiretor(Long id);
}

