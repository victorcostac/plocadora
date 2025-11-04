package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Ator;
import com.devweb.plocadora.domain.Classe;
import com.devweb.plocadora.domain.Diretor;
import com.devweb.plocadora.domain.Titulo;
import com.devweb.plocadora.infrastructure.repositories.AtorJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.ClasseJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.DiretorJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.TituloJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TituloService implements ITituloService {

    private final TituloJpaRepository tituloRepository;
    private final DiretorJpaRepository diretorRepository;
    private final ClasseJpaRepository classeRepository;
    private final AtorJpaRepository atorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Titulo> getTitulos() {
        return tituloRepository.findAllWithRelations();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Titulo> getTitulo(Long id) {
        return tituloRepository.findByIdWithRelations(id);
    }

    @Override
    @Transactional
    public Titulo createTitulo(String nome, Integer ano, String sinopse, String categoria,
                               Long diretorId, Long classeId, List<Long> atorIds) {
        Diretor diretor = diretorRepository.findById(diretorId)
                .orElseThrow(() -> new IllegalArgumentException("Diretor não encontrado com ID: " + diretorId));

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new IllegalArgumentException("Classe não encontrada com ID: " + classeId));

        List<Ator> atores = atorIds != null && !atorIds.isEmpty()
                ? atorRepository.findAllById(atorIds)
                : List.of();

        Titulo titulo = new Titulo(nome, ano, sinopse, categoria, diretor, classe, atores);
        return tituloRepository.save(titulo);
    }

    @Override
    @Transactional
    public Optional<Titulo> updateTitulo(Long id, String nome, Integer ano, String sinopse,
                                        String categoria, Long diretorId, Long classeId) {
        Optional<Titulo> tituloOptional = tituloRepository.findById(id);

        if (tituloOptional.isPresent()) {
            Diretor diretor = diretorRepository.findById(diretorId)
                    .orElseThrow(() -> new IllegalArgumentException("Diretor não encontrado com ID: " + diretorId));

            Classe classe = classeRepository.findById(classeId)
                    .orElseThrow(() -> new IllegalArgumentException("Classe não encontrada com ID: " + classeId));

            Titulo titulo = tituloOptional.get();
            titulo.update(nome, ano, sinopse, categoria, diretor, classe);
            return Optional.of(tituloRepository.save(titulo));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteTitulo(Long id) {
        if (tituloRepository.existsById(id)) {
            tituloRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

