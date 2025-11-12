package com.devweb.plocadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devweb.plocadora.domain.Classe;
import com.devweb.plocadora.infrastructure.repositories.ClasseJpaRepository;
import com.devweb.plocadora.web.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClasseService implements IClasseService {

    private final ClasseJpaRepository repository;

    public List<Classe> getClasses() {
        return repository.findAll();
    }

    public Classe getClasse(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classe não encontrada com o id " + id));
    }

    public Classe createClasse(String nome,@Valid Double valor,@Valid String prazoDevolucao) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da classe não pode ser vazio");
        }
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        if (prazoDevolucao == null || prazoDevolucao.trim().isEmpty()) {
            throw new IllegalArgumentException("Prazo de devolução não pode ser vazio");
        }

        try {
            Integer prazoDevolucaoInt = Integer.parseInt(prazoDevolucao);
            if (prazoDevolucaoInt <= 0) {
                throw new IllegalArgumentException("Prazo de devolução deve ser maior que zero");
            }

            Classe classe = new Classe(nome.trim(), valor, prazoDevolucaoInt);
            return repository.save(classe);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Prazo de devolução deve ser um número válido");
        }  catch (Exception e) {
            throw new InternalError("Problema na tentativa ao cadastrar classe.");
        }    
    }

    public Classe updateClasse(Long id, String nome, Double valor, String prazoDevolucao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da classe não pode ser vazio");
        }
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        if (prazoDevolucao == null || prazoDevolucao.trim().isEmpty()) {
            throw new IllegalArgumentException("Prazo de devolução não pode ser vazio");
        }

        try {
            Integer prazoDevolucaoInt = Integer.parseInt(prazoDevolucao);
            if (prazoDevolucaoInt <= 0) {
                throw new IllegalArgumentException("Prazo de devolução deve ser maior que zero");
            }

            Optional<Classe> classeOptional = repository.findById(id);
            if (!classeOptional.isPresent())
                throw new ResourceNotFoundException("Classe não encontrada com o id " + id);

            Classe classe = classeOptional.get();
            classe.setNome(nome.trim());
            classe.atualizarValor(valor);
            classe.setPrazoDevolucao(prazoDevolucaoInt);
            return repository.save(classe);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Prazo de devolução deve ser um número válido");
        }
    }

    public void deleteClasse(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Classe", id);
        }
        repository.deleteById(id);
    }

}
