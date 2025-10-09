package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Ator;
import com.devweb.plocadora.services.AtorService;
import com.devweb.plocadora.web.api.AtorApi;
import com.devweb.plocadora.web.model.AtorApiModel;
import com.devweb.plocadora.web.model.AtorCriadoApiModel;
import com.devweb.plocadora.web.model.NovoAtorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AtorController implements AtorApi {

    private final AtorService atorService;

    @Override
    public ResponseEntity<AtorCriadoApiModel> cadastroAtor(NovoAtorApiModel novoAtorApiModel) {
        try {
            if (novoAtorApiModel == null || novoAtorApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Ator ator = atorService.createAtor(novoAtorApiModel.getNome());

            AtorCriadoApiModel response = new AtorCriadoApiModel();
            response.setId(ator.getId().intValue());
            response.setNome(ator.getNome());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AtorApiModel> deleteAtor(String atorId) {
        try {
            Long id = Long.parseLong(atorId);
            Optional<Ator> atorOptional = atorService.getAtor(id);

            if (atorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            boolean deleted = atorService.deleteAtor(id);
            if (deleted) {
                Ator ator = atorOptional.get();
                AtorApiModel response = new AtorApiModel();
                response.setId(ator.getId().intValue());
                response.setNome(ator.getNome());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AtorApiModel> getAtor(String atorId) {
        try {
            Long id = Long.parseLong(atorId);
            Optional<Ator> atorOptional = atorService.getAtor(id);

            if (atorOptional.isPresent()) {
                Ator ator = atorOptional.get();
                AtorApiModel response = new AtorApiModel();
                response.setId(ator.getId().intValue());
                response.setNome(ator.getNome());
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
    public ResponseEntity<List<AtorApiModel>> getAtores() {
        try {
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AtorApiModel> putAtor(String atorId, AtorApiModel atorApiModel) {
        try {
            if (atorApiModel == null || atorApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(atorId);
            Optional<Ator> atorOptional = atorService.updateAtor(id, atorApiModel.getNome());

            if (atorOptional.isPresent()) {
                Ator ator = atorOptional.get();
                AtorApiModel response = new AtorApiModel();
                response.setId(ator.getId().intValue());
                response.setNome(ator.getNome());
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
}
