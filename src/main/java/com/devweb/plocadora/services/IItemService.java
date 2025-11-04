package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Item;
import com.devweb.plocadora.domain.TipoItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> getItens();
    Optional<Item> getItem(Long id);
    Item createItem(Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Long tituloId);
    Optional<Item> updateItem(Long id, Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Long tituloId);
    boolean deleteItem(Long id);
}

