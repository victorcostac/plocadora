package com.devweb.plocadora.services;

import java.util.List;
import java.util.Optional;

import com.devweb.plocadora.domain.Ator;

public interface IAtorService {

     List<Ator> getAtores();

     Optional<Ator> getAtor(Long id);
     Ator createAtor(String nome) ;

     Optional<Ator> updateAtor(Long id, String nome) ;

     boolean deleteAtor(Long id) ;
}
