package com.devweb.plocadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devweb.plocadora.domain.Ator;
import com.devweb.plocadora.infrastructure.repositories.AtorJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtorService implements IAtorService{

    private final AtorJpaRepository repository;

    public List<Ator> getAtores() {
        return repository.findAll();
    }

    public Optional<Ator> getAtor(Long id) {
        return repository.findById(id);
    }

    public Ator createAtor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do ator não pode ser vazio");
        }

        Ator ator = new Ator(nome.trim());
        System.out.println("ENTIDADE ATOR: "+ator);
        return repository.save(ator);
    }

    public Optional<Ator> updateAtor(Long id, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do ator não pode ser vazio");
        }

        Optional<Ator> atorOptional = repository.findById(id);
        if (atorOptional.isPresent()) {
            Ator ator = atorOptional.get();
            ator.atualizarNome(nome.trim());
            return Optional.of(repository.save(ator));
        }
        return Optional.empty();
    }

    public boolean deleteAtor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
