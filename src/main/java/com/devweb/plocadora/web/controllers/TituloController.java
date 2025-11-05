package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Titulo;
import com.devweb.plocadora.services.ITituloService;
import com.devweb.plocadora.web.api.TituloApi;
import com.devweb.plocadora.web.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TituloController implements TituloApi {

    private final ITituloService tituloService;

    @Override
    public ResponseEntity<Void> deleteTituloTituloId(String tituloId) {
        try {
            Long id = Long.parseLong(tituloId);
            boolean deleted = tituloService.deleteTitulo(id);

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
    public ResponseEntity<List<TituloApiModel>> getTitulo() {
        try {
            List<Titulo> titulos = tituloService.getTitulos();
            List<TituloApiModel> response = titulos.stream()
                    .map(this::mapToTituloApiModel)
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TituloApiModel> getTituloTituloId(String tituloId) {
        try {
            Long id = Long.parseLong(tituloId);
            Optional<Titulo> tituloOptional = tituloService.getTitulo(id);

            if (tituloOptional.isPresent()) {
                TituloApiModel response = mapToTituloApiModel(tituloOptional.get());
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
    public ResponseEntity<TituloCriadoApiModel> postTitulo(NovoTituloApiModel novoTituloApiModel) {
        try {
            if (novoTituloApiModel == null || novoTituloApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            List<Long> atorIds = novoTituloApiModel.getIdAtores() != null
                    ? novoTituloApiModel.getIdAtores().stream().map(Integer::longValue).toList()
                    : List.of();

            Titulo titulo = tituloService.createTitulo(
                    novoTituloApiModel.getNome(),
                    novoTituloApiModel.getAno().intValue(),
                    novoTituloApiModel.getSinopse(),
                    novoTituloApiModel.getCategoria(),
                    novoTituloApiModel.getIdDiretor().longValue(),
                    novoTituloApiModel.getIdClasse().longValue(),
                    atorIds);

            TituloCriadoApiModel response = mapToTituloCriadoApiModel(titulo);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TituloApiModel> putTituloTituloId(String tituloId, AtualizarTituloApiModel tituloApiModel) {
        try {
            if (tituloApiModel == null || tituloApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(tituloId);

            // Buscar o título existente
            Optional<Titulo> tituloExistente = tituloService.getTitulo(id);
            if (tituloExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Verificar se há relacionamentos para atualizar
            boolean hasRelations = tituloApiModel.getIdClasse() != null
                    || tituloApiModel.getIdDiretor() != null
                    || tituloApiModel.getIdAtores() != null;

            Optional<Titulo> tituloOptional;

            if (hasRelations) {
                // Converter IDs para Long, se fornecidos
                Long diretorId = tituloApiModel.getIdDiretor() != null
                        ? tituloApiModel.getIdDiretor().longValue()
                        : null;
                Long classeId = tituloApiModel.getIdClasse() != null
                        ? tituloApiModel.getIdClasse().longValue()
                        : null;

                List<Long> atorIds = tituloApiModel.getIdAtores() != null
                        ? tituloApiModel.getIdAtores().stream().map(Integer::longValue).toList()
                        : null;

                tituloOptional = tituloService.updateTituloWithRelations(
                        id,
                        tituloApiModel.getNome(),
                        tituloApiModel.getAno(),
                        tituloApiModel.getSinopse(),
                        tituloApiModel.getCategoria(),
                        diretorId,
                        classeId,
                        atorIds);
            } else {
                // Atualizar apenas os dados básicos
                tituloOptional = tituloService.updateTitulo(
                        id,
                        tituloApiModel.getNome(),
                        tituloApiModel.getAno(),
                        tituloApiModel.getSinopse(),
                        tituloApiModel.getCategoria());
            }

            if (tituloOptional.isPresent()) {
                TituloApiModel response = mapToTituloApiModel(tituloOptional.get());
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

    private TituloApiModel mapToTituloApiModel(Titulo titulo) {
        TituloApiModel model = new TituloApiModel();
        model.setId(titulo.getId().intValue());
        model.setNome(titulo.getNome());
        model.setAno(titulo.getAno());
        model.setSinopse(titulo.getSinopse());
        model.setCategoria(titulo.getCategoria());

        // Mapear diretor
        if (titulo.getDiretor() != null) {
            DiretorApiModel diretorModel = new DiretorApiModel();
            diretorModel.setId(titulo.getDiretor().getId().intValue());
            diretorModel.setNome(titulo.getDiretor().getNome());
            model.setDiretor(diretorModel);
        }

        // Mapear classe
        if (titulo.getClasse() != null) {
            ClasseApiModel classeModel = new ClasseApiModel();
            classeModel.setId(titulo.getClasse().getId().intValue());
            classeModel.setNome(titulo.getClasse().getNome());
            classeModel.setValor(titulo.getClasse().getValor());
            classeModel.setPrazoDevolucao(String.valueOf(titulo.getClasse().getPrazoDevolucao()));
            model.setClasse(classeModel);
        }

        // Mapear atores
        if (titulo.getAtores() != null && !titulo.getAtores().isEmpty()) {
            List<AtorApiModel> atoresModel = titulo.getAtores().stream()
                    .map(ator -> {
                        AtorApiModel atorModel = new AtorApiModel();
                        atorModel.setId(ator.getId().intValue());
                        atorModel.setNome(ator.getNome());
                        return atorModel;
                    })
                    .collect(Collectors.toList());
            model.setAtores(atoresModel);
        }

        return model;
    }

    private TituloCriadoApiModel mapToTituloCriadoApiModel(Titulo titulo) {
        TituloCriadoApiModel model = new TituloCriadoApiModel();
        model.setId(titulo.getId().intValue());
        model.setNome(titulo.getNome());
        model.setAno(titulo.getAno());
        model.setSinopse(titulo.getSinopse());
        model.setCategoria(titulo.getCategoria());

        // Mapear diretor
        if (titulo.getDiretor() != null) {
            DiretorApiModel diretorModel = new DiretorApiModel();
            diretorModel.setId(titulo.getDiretor().getId().intValue());
            diretorModel.setNome(titulo.getDiretor().getNome());
            model.setDiretor(diretorModel);
        }

        // Mapear classe
        if (titulo.getClasse() != null) {
            ClasseApiModel classeModel = new ClasseApiModel();
            classeModel.setId(titulo.getClasse().getId().intValue());
            classeModel.setNome(titulo.getClasse().getNome());
            classeModel.setValor(titulo.getClasse().getValor());
            classeModel.setPrazoDevolucao(String.valueOf(titulo.getClasse().getPrazoDevolucao()));
            model.setClasse(classeModel);
        }

        // Mapear atores
        if (titulo.getAtores() != null && !titulo.getAtores().isEmpty()) {
            List<AtorApiModel> atoresModel = titulo.getAtores().stream()
                    .map(ator -> {
                        AtorApiModel atorModel = new AtorApiModel();
                        atorModel.setId(ator.getId().intValue());
                        atorModel.setNome(ator.getNome());
                        return atorModel;
                    })
                    .collect(Collectors.toList());
            model.setAtores(atoresModel);
        }

        return model;
    }
}
