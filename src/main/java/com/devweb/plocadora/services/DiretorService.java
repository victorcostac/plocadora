package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Diretor;
import com.devweb.plocadora.infrastructure.repositories.DiretorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiretorService implements IDiretorService {

    private final DiretorJpaRepository diretorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Diretor> getDiretores() {
        return diretorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Diretor> getDiretor(Long id) {
        return diretorRepository.findById(id);
    }

    @Override
    @Transactional
    public Diretor createDiretor(String nome) {
        Diretor diretor = new Diretor(nome);
        return diretorRepository.save(diretor);
    }

    @Override
    @Transactional
    public Optional<Diretor> updateDiretor(Long id, String nome) {
        Optional<Diretor> diretorOptional = diretorRepository.findById(id);
        
        if (diretorOptional.isPresent()) {
            Diretor diretor = diretorOptional.get();
            diretor.atualizarNome(nome);
            return Optional.of(diretorRepository.save(diretor));
        }
        
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteDiretor(Long id) {
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

