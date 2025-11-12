package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Classe;
import com.devweb.plocadora.services.ClasseService;
import com.devweb.plocadora.web.api.ClasseApi;
import com.devweb.plocadora.web.model.AtualizarClasseApiModel;
import com.devweb.plocadora.web.model.ClasseApiModel;
import com.devweb.plocadora.web.model.ClasseCriadaApiModel;
import com.devweb.plocadora.web.model.NovaClasseApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClasseController implements ClasseApi {

    private final ClasseService classeService;

    @Override
    public ResponseEntity<ClasseCriadaApiModel> cadastroClasse(NovaClasseApiModel novaClasseApiModel) {
        try {
            if (novaClasseApiModel == null ||
                    novaClasseApiModel.getNome() == null ||
                    novaClasseApiModel.getValor() == null ||
                    novaClasseApiModel.getPrazoDevolucao() == null) {
                return ResponseEntity.badRequest().build();
            }

            Classe classe = classeService.createClasse(
                    novaClasseApiModel.getNome(),
                    novaClasseApiModel.getValor(),
                    novaClasseApiModel.getPrazoDevolucao());

            ClasseCriadaApiModel response = new ClasseCriadaApiModel();
            response.setId(Integer.valueOf(classe.getId().toString()));
            response.setNome(classe.getNome());
            response.setValor(classe.getValor().toString());
            response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteClasse(String classeId) {
        Long id = Long.parseLong(classeId);
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ClasseApiModel> getClasse(String classeId) {
            Long id = Long.parseLong(classeId);
            Classe classe = classeService.getClasse(id);
            ClasseApiModel response = new ClasseApiModel();
            response.setId(classe.getId().intValue());
            response.setNome(classe.getNome());
            response.setValor(classe.getValor());
            response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
            return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ClasseApiModel>> getClasses() {
        try {
            List<Classe> classes = classeService.getClasses();
            List<ClasseApiModel> response = classes.stream()
                    .map(classe -> {
                        ClasseApiModel model = new ClasseApiModel();
                        model.setId(classe.getId().intValue());
                        model.setNome(classe.getNome());
                        model.setValor(classe.getValor());
                        model.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
                        return model;
                    })
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ClasseApiModel> putClasse(String classeId, AtualizarClasseApiModel classeApiModel) {
        if (classeApiModel == null ||
                classeApiModel.getNome() == null ||
                classeApiModel.getValor() == null ||
                classeApiModel.getPrazoDevolucao() == null) {
            throw new IllegalArgumentException("Informações da classe não informadas.");
        }

        Long id = Long.parseLong(classeId);
        Classe classe = classeService.updateClasse(
                id,
                classeApiModel.getNome(),
                classeApiModel.getValor(),
                classeApiModel.getPrazoDevolucao());

        ClasseApiModel response = new ClasseApiModel();
        response.setId(classe.getId().intValue());
        response.setNome(classe.getNome());
        response.setValor(classe.getValor());
        response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
        return ResponseEntity.ok(response);

    }
}
