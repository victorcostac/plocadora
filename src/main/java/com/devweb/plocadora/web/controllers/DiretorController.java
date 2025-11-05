package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Diretor;
import com.devweb.plocadora.services.IDiretorService;
import com.devweb.plocadora.web.api.DiretorApi;
import com.devweb.plocadora.web.exception.ResourceNotFoundException;
import com.devweb.plocadora.web.model.AtualizarDiretorApiModel;
import com.devweb.plocadora.web.model.DiretorApiModel;
import com.devweb.plocadora.web.model.DiretorCriadoApiModel;
import com.devweb.plocadora.web.model.NovoDiretorApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiretorController implements DiretorApi {

    private final IDiretorService diretorService;

    @Override
    public ResponseEntity<Void> deleteDiretor(String diretorId) {
        Long id = Long.parseLong(diretorId);
        boolean deleted = diretorService.deleteDiretor(id);

        if (!deleted) {
            throw new ResourceNotFoundException("Diretor", id);
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DiretorApiModel> getDiretor(String diretorId) {
        Long id = Long.parseLong(diretorId);
        Diretor diretor = diretorService.getDiretor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretor", id));

        DiretorApiModel response = new DiretorApiModel();
        response.setId(diretor.getId().intValue());
        response.setNome(diretor.getNome());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<DiretorApiModel>> getDiretores() {
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
    }

    @Override
    public ResponseEntity<DiretorCriadoApiModel> postDiretor(NovoDiretorApiModel novoDiretorApiModel) {
        if (novoDiretorApiModel == null || novoDiretorApiModel.getNome() == null) {
            throw new IllegalArgumentException("Nome do diretor é obrigatório");
        }

        Diretor diretor = diretorService.createDiretor(novoDiretorApiModel.getNome());

        DiretorCriadoApiModel response = new DiretorCriadoApiModel();
        response.setId(diretor.getId().intValue());
        response.setNome(diretor.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<DiretorApiModel> putDiretor(String diretorId, AtualizarDiretorApiModel diretorApiModel) {
        if (diretorApiModel == null || diretorApiModel.getNome() == null) {
            throw new IllegalArgumentException("Nome do diretor é obrigatório");
        }

        Long id = Long.parseLong(diretorId);
        Diretor diretor = diretorService.updateDiretor(id, diretorApiModel.getNome())
                .orElseThrow(() -> new ResourceNotFoundException("Diretor", id));

        DiretorApiModel response = new DiretorApiModel();
        response.setId(diretor.getId().intValue());
        response.setNome(diretor.getNome());
        return ResponseEntity.ok(response);
    }
}
