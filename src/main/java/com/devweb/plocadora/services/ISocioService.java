package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Socio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISocioService {
    List<Socio> getSocios();
    Optional<Socio> getSocio(Long id);
    Optional<Socio> getSocioWithDependentes(Long id);
    Socio createSocio(Long numInscricao, String nome, LocalDate dtNascimento, String sexo,
                      Boolean ativo, String cpf, String endereco, String tel);
    Optional<Socio> updateSocio(Long id, String nome, LocalDate dtNascimento, String sexo,
                                Boolean ativo, String cpf, String endereco, String tel);
    boolean deleteSocio(Long id);
}

