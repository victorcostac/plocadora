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
            
            List<Long> atorIds = novoTituloApiModel.getIdAtor() != null 
                ? List.of(novoTituloApiModel.getIdAtor().longValue()) 
                : List.of();
            
            Titulo titulo = tituloService.createTitulo(
                novoTituloApiModel.getNome(),
                novoTituloApiModel.getAno().intValue(),
                novoTituloApiModel.getSinopse(),
                novoTituloApiModel.getCategoria(),
                novoTituloApiModel.getIdDiretor().longValue(),
                novoTituloApiModel.getIdClasse().longValue(),
                atorIds
            );
            
            TituloCriadoApiModel response = mapToTituloCriadoApiModel(titulo);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TituloApiModel> putTituloTituloId(String tituloId, TituloApiModel tituloApiModel) {
        try {
            if (tituloApiModel == null || tituloApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Long id = Long.parseLong(tituloId);
            
            // Buscar o título existente para manter os relacionamentos
            Optional<Titulo> tituloExistente = tituloService.getTitulo(id);
            if (tituloExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            // Extrair IDs dos relacionamentos do modelo ou manter os existentes
            Long diretorId = tituloApiModel.getDiretor() != null && tituloApiModel.getDiretor().getId() != null
                ? tituloApiModel.getDiretor().getId().longValue()
                : tituloExistente.get().getDiretor().getId();

            Long classeId = tituloApiModel.getClasse() != null && tituloApiModel.getClasse().getId() != null
                ? tituloApiModel.getClasse().getId().longValue()
                : tituloExistente.get().getClasse().getId();

            Optional<Titulo> tituloOptional = tituloService.updateTitulo(
                id,
                tituloApiModel.getNome(),
                Integer.parseInt(tituloApiModel.getAno()),
                tituloApiModel.getSinopse(),
                tituloApiModel.getCategoria(),
                diretorId,
                classeId
            );
            
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
        model.setAno(String.valueOf(titulo.getAno()));
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
        if (titulo.getAtor() != null && !titulo.getAtor().isEmpty()) {
            List<AtorApiModel> atoresModel = titulo.getAtor().stream()
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
        model.setAno(java.math.BigDecimal.valueOf(titulo.getAno()));
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
        if (titulo.getAtor() != null && !titulo.getAtor().isEmpty()) {
            List<AtorApiModel> atoresModel = titulo.getAtor().stream()
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
