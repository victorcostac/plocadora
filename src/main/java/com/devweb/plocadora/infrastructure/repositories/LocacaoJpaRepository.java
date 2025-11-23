package com.devweb.plocadora.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devweb.plocadora.domain.Locacao;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LocacaoJpaRepository extends JpaRepository<Locacao, Long> {

       @Query("SELECT DISTINCT l FROM Locacao l " +
                     "LEFT JOIN FETCH l.item " +
                     "LEFT JOIN FETCH l.cliente")
       List<Locacao> findAllWithRelations();

       @Query("SELECT l FROM Locacao l " +
                     "LEFT JOIN FETCH l.item " +
                     "LEFT JOIN FETCH l.cliente " +
                     "WHERE l.id = :id")
       Optional<Locacao> findByIdWithRelations(@Param("id") Long id);
}
