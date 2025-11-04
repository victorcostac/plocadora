package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Socio;
import com.devweb.plocadora.infrastructure.repositories.SocioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocioService implements ISocioService {

    private final SocioJpaRepository socioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Socio> getSocios() {
        return socioRepository.findAllWithDependentes();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Socio> getSocio(Long id) {
        return socioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Socio> getSocioWithDependentes(Long id) {
        return socioRepository.findByIdWithDependentes(id);
    }

    @Override
    @Transactional
    public Socio createSocio(Long numInscricao, String nome, LocalDate dtNascimento, String sexo,
                            Boolean ativo, String cpf, String endereco, String tel) {
        // Validações
        if (numInscricao == null || numInscricao <= 0) {
            throw new IllegalArgumentException("Número de inscrição inválido");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        if (socioRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Socio socio = new Socio(numInscricao, nome, dtNascimento, sexo, ativo, cpf, endereco, tel);
        return socioRepository.save(socio);
    }

    @Override
    @Transactional
    public Optional<Socio> updateSocio(Long id, String nome, LocalDate dtNascimento, String sexo,
                                       Boolean ativo, String cpf, String endereco, String tel) {
        Optional<Socio> socioOptional = socioRepository.findById(id);

        if (socioOptional.isPresent()) {
            Socio socio = socioOptional.get();

            // Verificar se CPF já existe para outro sócio
            if (!socio.getCpf().equals(cpf) && socioRepository.existsByCpf(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado para outro sócio");
            }

            socio.atualizarDados(nome, dtNascimento, sexo, ativo);
            socio.atualizarDadosSocio(cpf, endereco, tel);
            return Optional.of(socioRepository.save(socio));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteSocio(Long id) {
        if (socioRepository.existsById(id)) {
            socioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

