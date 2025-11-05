package com.devweb.plocadora.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.plocadora.domain.Dependente;
import com.devweb.plocadora.services.IDependenteService;
import com.devweb.plocadora.web.api.DependenteApi;
import com.devweb.plocadora.web.model.AtualizarDependenteApiModel;
import com.devweb.plocadora.web.model.DependenteApiModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DependenteController implements DependenteApi {

    private final IDependenteService dependenteService;

    @Override
    public ResponseEntity<Void> deleteDependenteDependenteId(String dependenteId) {
        try {
            Long id = Long.parseLong(dependenteId);
            boolean deleted = dependenteService.deleteDependente(id);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<DependenteApiModel>> getDependente() {
        try {
            List<Dependente> dependentes = dependenteService.getDependentes();
            List<DependenteApiModel> response = dependentes.stream()
                    .map(this::mapToDependenteApiModel)
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<DependenteApiModel> getDependenteDependenteId(String dependenteId) {
        try {
            Long id = Long.parseLong(dependenteId);
            Optional<Dependente> dependenteOptional = dependenteService.getDependente(id);

            if (dependenteOptional.isPresent()) {
                DependenteApiModel response = mapToDependenteApiModel(dependenteOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<DependenteApiModel> postDependente() {
        // Nota: A especificação OpenAPI não define o requestBody para POST /dependente
        // Retornando 400 Bad Request pois falta o corpo da requisição
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<DependenteApiModel> putDependenteDependenteId(String dependenteId,
            @Valid AtualizarDependenteApiModel atualizarDependenteApiModel) {
        try {
            if (atualizarDependenteApiModel == null || atualizarDependenteApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(dependenteId);

            // Assumindo que socio_id não está sendo atualizado (apenas os dados do
            // dependente)
            Optional<Dependente> dependenteOptional = dependenteService.updateDependente(
                    id,
                    atualizarDependenteApiModel.getNome(),
                    atualizarDependenteApiModel.getDtNascimento(),
                    atualizarDependenteApiModel.getSexo(),
                    atualizarDependenteApiModel.getAtivo(),
                    null // Mantém o sócio atual
            );

            if (dependenteOptional.isPresent()) {
                DependenteApiModel response = mapToDependenteApiModel(dependenteOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private DependenteApiModel mapToDependenteApiModel(Dependente dependente) {
        DependenteApiModel model = new DependenteApiModel();
        model.setId(dependente.getId().intValue());
        model.setNumInscricao(dependente.getNumInscricao().intValue());
        model.setNome(dependente.getNome());
        model.setDtNascimento(dependente.getDtNascimento());
        model.setSexo(dependente.getSexo());
        model.setAtivo(dependente.getAtivo());
        return model;
    }

}
