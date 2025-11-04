package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Titulo;
import com.devweb.plocadora.services.ITituloService;
import com.devweb.plocadora.web.api.TituloApi;
import com.devweb.plocadora.web.model.NovoTituloApiModel;
import com.devweb.plocadora.web.model.TituloApiModel;
import com.devweb.plocadora.web.model.TituloCriadoApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
                    .map(titulo -> {
                        TituloApiModel model = new TituloApiModel();
                        model.setNome(titulo.getNome());
                        model.setAno(String.valueOf(titulo.getAno()));
                        model.setSinopse(titulo.getSinopse());
                        model.setCategoria(titulo.getCategoria());
                        return model;
                    })
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
                Titulo titulo = tituloOptional.get();
                TituloApiModel response = new TituloApiModel();
                response.setNome(titulo.getNome());
                response.setAno(String.valueOf(titulo.getAno()));
                response.setSinopse(titulo.getSinopse());
                response.setCategoria(titulo.getCategoria());
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
            
            TituloCriadoApiModel response = new TituloCriadoApiModel();
            response.setId(titulo.getId().intValue());
            response.setNome(titulo.getNome());
            response.setAno(titulo.getAno().doubleValue());
            response.setSinopse(titulo.getSinopse());
            response.setCategoria(titulo.getCategoria());
            
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
            
            // Para o update, precisamos extrair IDs dos relacionamentos
            // Como o modelo da API não tem essas informações completas,
            // vamos buscar o título existente e manter os relacionamentos
            Optional<Titulo> tituloExistente = tituloService.getTitulo(id);
            if (tituloExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Optional<Titulo> tituloOptional = tituloService.updateTitulo(
                id,
                tituloApiModel.getNome(),
                Integer.parseInt(tituloApiModel.getAno()),
                tituloApiModel.getSinopse(),
                tituloApiModel.getCategoria(),
                tituloExistente.get().getDiretor().getId(),
                tituloExistente.get().getClasse().getId()
            );
            
            if (tituloOptional.isPresent()) {
                Titulo titulo = tituloOptional.get();
                TituloApiModel response = new TituloApiModel();
                response.setNome(titulo.getNome());
                response.setAno(String.valueOf(titulo.getAno()));
                response.setSinopse(titulo.getSinopse());
                response.setCategoria(titulo.getCategoria());
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
