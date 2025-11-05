package com.devweb.plocadora.infrastructure.repositories;

import com.devweb.plocadora.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ItemJpaRepository extends JpaRepository<Item, Long> {

       @Query("SELECT i FROM Item i " +
                     "LEFT JOIN FETCH i.titulo t " +
                     "LEFT JOIN FETCH t.diretor " +
                     "LEFT JOIN FETCH t.classe " +
                     "LEFT JOIN FETCH t.atores")
       List<Item> findAllWithTitulo();

       @Query("SELECT i FROM Item i " +
                     "LEFT JOIN FETCH i.titulo t " +
                     "LEFT JOIN FETCH t.diretor " +
                     "LEFT JOIN FETCH t.classe " +
                     "LEFT JOIN FETCH t.atores " +
                     "WHERE i.id = :id")
       Optional<Item> findByIdWithTitulo(@Param("id") Long id);
}
