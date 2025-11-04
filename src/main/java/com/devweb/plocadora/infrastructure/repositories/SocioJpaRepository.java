package com.devweb.plocadora.infrastructure.repositories;

import com.devweb.plocadora.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SocioJpaRepository extends JpaRepository<Socio, Long> {

    @Query("SELECT DISTINCT s FROM Socio s LEFT JOIN FETCH s.dependentes")
    List<Socio> findAllWithDependentes();

    @Query("SELECT s FROM Socio s LEFT JOIN FETCH s.dependentes WHERE s.id = :id")
    Optional<Socio> findByIdWithDependentes(@Param("id") Long id);

    Optional<Socio> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}

