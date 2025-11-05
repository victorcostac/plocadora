package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Ator;
import com.devweb.plocadora.services.IAtorService;
import com.devweb.plocadora.web.api.AtorApi;
import com.devweb.plocadora.web.exception.ResourceNotFoundException;
import com.devweb.plocadora.web.model.AtorApiModel;
import com.devweb.plocadora.web.model.AtorCriadoApiModel;
import com.devweb.plocadora.web.model.AtualizarAtorApiModel;
import com.devweb.plocadora.web.model.NovoAtorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AtorController implements AtorApi {

    private final IAtorService atorService;

    @Override
    public ResponseEntity<AtorCriadoApiModel> cadastroAtor(NovoAtorApiModel novoAtorApiModel) {
        if (novoAtorApiModel == null || novoAtorApiModel.getNome() == null) {
            throw new IllegalArgumentException("Nome do ator é obrigatório");
        }

        Ator ator = atorService.createAtor(novoAtorApiModel.getNome());

        AtorCriadoApiModel response = new AtorCriadoApiModel();
        response.setId(ator.getId().intValue());
        response.setNome(ator.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteAtor(String atorId) {
        Long id = Long.parseLong(atorId);
        boolean deleted = atorService.deleteAtor(id);

        if (!deleted) {
            throw new ResourceNotFoundException("Ator", id);
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AtorApiModel> getAtor(String atorId) {
        Long id = Long.parseLong(atorId);
        Ator ator = atorService.getAtor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ator", id));

        AtorApiModel response = new AtorApiModel();
        response.setId(ator.getId().intValue());
        response.setNome(ator.getNome());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<AtorApiModel>> getAtores() {
        List<Ator> atores = atorService.getAtores();
        List<AtorApiModel> response = atores.stream()
                .map(ator -> {
                    AtorApiModel model = new AtorApiModel();
                    model.setId(ator.getId().intValue());
                    model.setNome(ator.getNome());
                    return model;
                })
                .toList();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AtorApiModel> putAtor(String atorId, AtualizarAtorApiModel atorApiModel) {
        if (atorApiModel == null || atorApiModel.getNome() == null) {
            throw new IllegalArgumentException("Nome do ator é obrigatório");
        }

        Long id = Long.parseLong(atorId);
        Ator ator = atorService.updateAtor(id, atorApiModel.getNome())
                .orElseThrow(() -> new ResourceNotFoundException("Ator", id));

        AtorApiModel response = new AtorApiModel();
        response.setId(ator.getId().intValue());
        response.setNome(ator.getNome());

        return ResponseEntity.ok(response);
    }
}
