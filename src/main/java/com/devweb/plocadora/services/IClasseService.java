package com.devweb.plocadora.services;

import java.util.List;
import java.util.Optional;

import com.devweb.plocadora.domain.Classe;

public interface IClasseService {
    public List<Classe> getClasses() ;
    public Optional<Classe> getClasse(Long id) ;
    public Classe createClasse(String nome, Double valor, String prazoDevolucao) ;

    public Optional<Classe> updateClasse(Long id, String nome, Double valor, String prazoDevolucao) ;

    public boolean deleteClasse(Long id) ;
}
