package com.devweb.plocadora.web.controllers;

import com.devweb.plocadora.domain.Item;
import com.devweb.plocadora.domain.TipoItem;
import com.devweb.plocadora.services.IItemService;
import com.devweb.plocadora.web.api.ItemApi;
import com.devweb.plocadora.web.model.ItemApiModel;
import com.devweb.plocadora.web.model.ItemCriadoApiModel;
import com.devweb.plocadora.web.model.NovoItemApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {

    private final IItemService itemService;

    @Override
    public ResponseEntity<Void> deleteItemItemId(String itemId) {
        try {
            Long id = Long.parseLong(itemId);
            boolean deleted = itemService.deleteItem(id);
            
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
    public ResponseEntity<List<ItemApiModel>> getItem() {
        try {
            List<Item> itens = itemService.getItens();
            List<ItemApiModel> response = itens.stream()
                    .map(item -> {
                        ItemApiModel model = new ItemApiModel();
                        model.setId(item.getId().intValue());
                        model.setNumSerie(String.valueOf(item.getNumSerie()));
                        model.setDtAquisicao(LocalDate.from(item.getDtAquisicao()));
                        model.setTipoItem(item.getTipo().name());
                        return model;
                    })
                    .toList();
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ItemApiModel> getItemItemId(String itemId) {
        try {
            Long id = Long.parseLong(itemId);
            Optional<Item> itemOptional = itemService.getItem(id);
            
            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
                ItemApiModel response = new ItemApiModel();
                response.setId(item.getId().intValue());
                response.setNumSerie(String.valueOf(item.getNumSerie()));
                response.setDtAquisicao(LocalDate.from(item.getDtAquisicao()));
                response.setTipoItem(item.getTipo().name());
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
    public ResponseEntity<ItemCriadoApiModel> postItem(NovoItemApiModel novoItemApiModel) {
        try {
            if (novoItemApiModel == null || novoItemApiModel.getNumSerie() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            // Converter string para TipoItem enum
            TipoItem tipo = TipoItem.valueOf(novoItemApiModel.getTipoItem().toUpperCase());
            
            // Converter LocalDate para LocalDateTime
            LocalDateTime dtAquisicao = novoItemApiModel.getDtAquisicao().atStartOfDay();
            
            // Usar tituloId do modelo da API
            Long tituloId = novoItemApiModel.getTituloId() != null 
                ? novoItemApiModel.getTituloId().longValue() 
                : null;
            
            if (tituloId == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Item item = itemService.createItem(
                novoItemApiModel.getNumSerie().longValue(),
                dtAquisicao,
                tipo,
                tituloId
            );
            
            ItemCriadoApiModel response = new ItemCriadoApiModel();
            response.setId(item.getId().intValue());
            response.setNumSerie(String.valueOf(item.getNumSerie()));
            response.setDtAquisicao(LocalDate.from(item.getDtAquisicao()));
            response.setTipoItem(item.getTipo().name());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ItemApiModel> putItemItemId(String itemId, ItemApiModel itemApiModel) {
        try {
            if (itemApiModel == null || itemApiModel.getNumSerie() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Long id = Long.parseLong(itemId);
            
            // Converter string para TipoItem enum
            TipoItem tipo = TipoItem.valueOf(itemApiModel.getTipoItem().toUpperCase());
            
            // Converter LocalDate para LocalDateTime
            LocalDateTime dtAquisicao = itemApiModel.getDtAquisicao().atStartOfDay();
            
            // Buscar o item existente para manter o titulo_id
            Optional<Item> itemExistente = itemService.getItem(id);
            if (itemExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Optional<Item> itemOptional = itemService.updateItem(
                id,
                Long.parseLong(itemApiModel.getNumSerie()),
                dtAquisicao,
                tipo,
                itemExistente.get().getTitulo().getId()
            );
            
            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
                ItemApiModel response = new ItemApiModel();
                response.setId(item.getId().intValue());
                response.setNumSerie(String.valueOf(item.getNumSerie()));
                response.setDtAquisicao(LocalDate.from(item.getDtAquisicao()));
                response.setTipoItem(item.getTipo().name());
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
