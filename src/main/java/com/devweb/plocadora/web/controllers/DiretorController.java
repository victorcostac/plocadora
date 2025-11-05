package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Diretor;
import com.devweb.plocadora.services.IDiretorService;
import com.devweb.plocadora.web.api.DiretorApi;
import com.devweb.plocadora.web.model.AtualizarDiretorApiModel;
import com.devweb.plocadora.web.model.DiretorApiModel;
import com.devweb.plocadora.web.model.DiretorCriadoApiModel;
import com.devweb.plocadora.web.model.NovoDiretorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DiretorController implements DiretorApi {

    private final IDiretorService diretorService;

    @Override
    public ResponseEntity<Void> deleteDiretor(String diretorId) {
        try {
            Long id = Long.parseLong(diretorId);
            boolean deleted = diretorService.deleteDiretor(id);

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
    public ResponseEntity<DiretorApiModel> getDiretor(String diretorId) {
        try {
            Long id = Long.parseLong(diretorId);
            Optional<Diretor> diretorOptional = diretorService.getDiretor(id);

            if (diretorOptional.isPresent()) {
                Diretor diretor = diretorOptional.get();
                DiretorApiModel response = new DiretorApiModel();
                response.setId(diretor.getId().intValue());
                response.setNome(diretor.getNome());
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
    public ResponseEntity<List<DiretorApiModel>> getDiretores() {
        try {
            List<Diretor> diretores = diretorService.getDiretores();
            List<DiretorApiModel> response = diretores.stream()
                    .map(diretor -> {
                        DiretorApiModel model = new DiretorApiModel();
                        model.setId(diretor.getId().intValue());
                        model.setNome(diretor.getNome());
                        return model;
                    })
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<DiretorCriadoApiModel> postDiretor(NovoDiretorApiModel novoDiretorApiModel) {
        try {
            if (novoDiretorApiModel == null || novoDiretorApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Diretor diretor = diretorService.createDiretor(novoDiretorApiModel.getNome());

            DiretorCriadoApiModel response = new DiretorCriadoApiModel();
            response.setId(diretor.getId().intValue());
            response.setNome(diretor.getNome());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<DiretorApiModel> putDiretor(String diretorId, AtualizarDiretorApiModel diretorApiModel) {
        try {
            if (diretorApiModel == null || diretorApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(diretorId);
            Optional<Diretor> diretorOptional = diretorService.updateDiretor(id, diretorApiModel.getNome());

            if (diretorOptional.isPresent()) {
                Diretor diretor = diretorOptional.get();
                DiretorApiModel response = new DiretorApiModel();
                response.setId(diretor.getId().intValue());
                response.setNome(diretor.getNome());
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
}

