package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Item;
import com.devweb.plocadora.domain.TipoItem;
import com.devweb.plocadora.domain.Titulo;
import com.devweb.plocadora.infrastructure.repositories.ItemJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.TituloJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

    private final ItemJpaRepository itemRepository;
    private final TituloJpaRepository tituloRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItens() {
        return itemRepository.findAllWithTitulo();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Item> getItem(Long id) {
        return itemRepository.findByIdWithTitulo(id);
    }

    @Override
    @Transactional
    public Item createItem(Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Long tituloId) {
        Titulo titulo = tituloRepository.findById(tituloId)
                .orElseThrow(() -> new IllegalArgumentException("Título não encontrado com ID: " + tituloId));

        Item item = new Item(numSerie, dtAquisicao, tipo, titulo);
        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public Optional<Item> updateItem(Long id, Long numSerie, LocalDateTime dtAquisicao, TipoItem tipo, Long tituloId) {
        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isPresent()) {
            Titulo titulo = tituloRepository.findById(tituloId)
                    .orElseThrow(() -> new IllegalArgumentException("Título não encontrado com ID: " + tituloId));

            Item item = itemOptional.get();
            item.update(numSerie, dtAquisicao, tipo, titulo);
            return Optional.of(itemRepository.save(item));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

