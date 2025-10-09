package com.devweb.plocadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devweb.plocadora.domain.Diretor;
import com.devweb.plocadora.infrastructure.repositories.DiretorJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiretorService {

    private final DiretorJpaRepository repository;

    public List<Diretor> getDiretores() {
        return repository.findAll();
    }

    public Optional<Diretor> getDiretor(Long id) {
        return repository.findById(id);
    }

    public Diretor createDiretor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do diretor não pode ser vazio");
        }

        Diretor diretor = new Diretor(nome.trim());
        return repository.save(diretor);
    }

    public Optional<Diretor> updateDiretor(Long id, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do diretor não pode ser vazio");
        }

        Optional<Diretor> diretorOptional = repository.findById(id);
        if (diretorOptional.isPresent()) {
            Diretor diretor = diretorOptional.get();
            diretor.setNome(nome.trim());
            return Optional.of(repository.save(diretor));
        }
        return Optional.empty();
    }

    public boolean deleteDiretor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
