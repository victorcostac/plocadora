package com.devweb.plocadora.infrastructure.repositories;


import com.devweb.plocadora.domain.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AtorJpaRepository extends JpaRepository<Ator, Long> {
}
