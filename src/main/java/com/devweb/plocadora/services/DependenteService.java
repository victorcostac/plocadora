package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Dependente;
import com.devweb.plocadora.domain.Socio;
import com.devweb.plocadora.infrastructure.repositories.DependenteJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.SocioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService implements IDependenteService {

    private final DependenteJpaRepository dependenteRepository;
    private final SocioJpaRepository socioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Dependente> getDependentes() {
        return dependenteRepository.findAllWithSocio();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dependente> getDependente(Long id) {
        return dependenteRepository.findByIdWithSocio(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dependente> getDependentesBySocioId(Long socioId) {
        return dependenteRepository.findBySocioId(socioId);
    }

    @Override
    @Transactional
    public Dependente createDependente(Long numInscricao, String nome, LocalDate dtNascimento,
                                       String sexo, Boolean ativo, Long socioId) {
        // Validações
        if (numInscricao == null || numInscricao <= 0) {
            throw new IllegalArgumentException("Número de inscrição inválido");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (socioId == null) {
            throw new IllegalArgumentException("Sócio ID não pode ser nulo");
        }

        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new IllegalArgumentException("Sócio não encontrado com ID: " + socioId));

        Dependente dependente = new Dependente(numInscricao, nome, dtNascimento, sexo, ativo, socio);
        return dependenteRepository.save(dependente);
    }

    @Override
    @Transactional
    public Optional<Dependente> updateDependente(Long id, String nome, LocalDate dtNascimento,
                                                 String sexo, Boolean ativo, Long socioId) {
        Optional<Dependente> dependenteOptional = dependenteRepository.findById(id);

        if (dependenteOptional.isPresent()) {
            Dependente dependente = dependenteOptional.get();

            // Atualizar dados base
            dependente.atualizarDados(nome, dtNascimento, sexo, ativo);

            // Atualizar sócio se fornecido
            if (socioId != null && !dependente.getSocio().getId().equals(socioId)) {
                Socio novoSocio = socioRepository.findById(socioId)
                        .orElseThrow(() -> new IllegalArgumentException("Sócio não encontrado com ID: " + socioId));
                dependente.atualizarSocio(novoSocio);
            }

            return Optional.of(dependenteRepository.save(dependente));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteDependente(Long id) {
        if (dependenteRepository.existsById(id)) {
            dependenteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
