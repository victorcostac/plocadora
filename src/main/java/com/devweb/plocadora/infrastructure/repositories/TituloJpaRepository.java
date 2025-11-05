package com.devweb.plocadora.infrastructure.repositories;

import com.devweb.plocadora.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TituloJpaRepository extends JpaRepository<Titulo, Long> {

       @Query("SELECT DISTINCT t FROM Titulo t " +
                     "LEFT JOIN FETCH t.diretor " +
                     "LEFT JOIN FETCH t.classe " +
                     "LEFT JOIN FETCH t.atores")
       List<Titulo> findAllWithRelations();

       @Query("SELECT t FROM Titulo t " +
                     "LEFT JOIN FETCH t.diretor " +
                     "LEFT JOIN FETCH t.classe " +
                     "LEFT JOIN FETCH t.atores " +
                     "WHERE t.id = :id")
       Optional<Titulo> findByIdWithRelations(@Param("id") Long id);
}
