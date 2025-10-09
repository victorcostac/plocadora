package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Classe;
import com.devweb.plocadora.services.ClasseService;
import com.devweb.plocadora.web.api.ClasseApi;
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
    public ResponseEntity<ClasseApiModel> deleteClasse(String classeId) {
        try {
            Long id = Long.parseLong(classeId);
            Optional<Classe> classeOptional = classeService.getClasse(id);

            if (classeOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            boolean deleted = classeService.deleteClasse(id);
            if (deleted) {
                Classe classe = classeOptional.get();
                ClasseApiModel response = new ClasseApiModel();
                response.setNome(classe.getNome());
                response.setValor(classe.getValor());
                response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
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
    public ResponseEntity<ClasseApiModel> getClasse(String classeId) {
        try {
            Long id = Long.parseLong(classeId);
            Optional<Classe> classeOptional = classeService.getClasse(id);

            if (classeOptional.isPresent()) {
                Classe classe = classeOptional.get();
                ClasseApiModel response = new ClasseApiModel();
                response.setNome(classe.getNome());
                response.setValor(classe.getValor());
                response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
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
    public ResponseEntity<List<ClasseApiModel>> getClasses() {
        try {
            List<Classe> classes = classeService.getClasses();
            List<ClasseApiModel> response = classes.stream()
                    .map(classe -> {
                        ClasseApiModel model = new ClasseApiModel();
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
    public ResponseEntity<ClasseApiModel> putClasse(String classeId, ClasseApiModel classeApiModel) {
        try {
            if (classeApiModel == null ||
                    classeApiModel.getNome() == null ||
                    classeApiModel.getValor() == null ||
                    classeApiModel.getPrazoDevolucao() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(classeId);
            Optional<Classe> classeOptional = classeService.updateClasse(
                    id,
                    classeApiModel.getNome(),
                    classeApiModel.getValor(),
                    classeApiModel.getPrazoDevolucao());

            if (classeOptional.isPresent()) {
                Classe classe = classeOptional.get();
                ClasseApiModel response = new ClasseApiModel();
                response.setNome(classe.getNome());
                response.setValor(classe.getValor());
                response.setPrazoDevolucao(classe.getPrazoDevolucao().toString());
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
