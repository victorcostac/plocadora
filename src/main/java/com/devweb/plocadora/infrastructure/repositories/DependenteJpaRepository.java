package com.devweb.plocadora.infrastructure.repositories;

import com.devweb.plocadora.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DependenteJpaRepository extends JpaRepository<Dependente, Long> {

    @Query("SELECT DISTINCT d FROM Dependente d LEFT JOIN FETCH d.socio")
    List<Dependente> findAllWithSocio();

    @Query("SELECT d FROM Dependente d LEFT JOIN FETCH d.socio WHERE d.id = :id")
    Optional<Dependente> findByIdWithSocio(@Param("id") Long id);

    @Query("SELECT d FROM Dependente d LEFT JOIN FETCH d.socio WHERE d.socio.id = :socioId")
    List<Dependente> findBySocioId(@Param("socioId") Long socioId);
}
