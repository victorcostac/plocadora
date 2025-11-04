package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Dependente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDependenteService {
    List<Dependente> getDependentes();
    Optional<Dependente> getDependente(Long id);
    List<Dependente> getDependentesBySocioId(Long socioId);
    Dependente createDependente(Long numInscricao, String nome, LocalDate dtNascimento,
                                String sexo, Boolean ativo, Long socioId);
    Optional<Dependente> updateDependente(Long id, String nome, LocalDate dtNascimento,
                                          String sexo, Boolean ativo, Long socioId);
    boolean deleteDependente(Long id);
}
