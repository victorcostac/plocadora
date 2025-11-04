package com.devweb.plocadora.examples;

import com.devweb.plocadora.domain.Dependente;
import com.devweb.plocadora.domain.Socio;
import com.devweb.plocadora.infrastructure.repositories.DependenteJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.SocioJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Exemplo de uso das entidades Cliente, Socio e Dependente
 * Demonstra o mapeamento de herança JOINED e o relacionamento 1:N
 *
 * Para ativar: descomente @Component
 */
//@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteHerancaExemplo implements CommandLineRunner {

    private final SocioJpaRepository socioRepository;
    private final DependenteJpaRepository dependenteRepository;

    @Override
    public void run(String... args) {
        log.info("========================================");
        log.info("🎯 EXEMPLO: Herança JOINED - Cliente/Socio/Dependente");
        log.info("========================================");

        // 1. Criar um Sócio
        log.info("\n1️⃣ Criando Sócio...");
        Socio socio = new Socio(
            1001L,                      // numInscricao
            "João Silva",               // nome
            LocalDate.of(1980, 5, 15),  // dtNascimento
            "Masculino",                // sexo
            true,                       // ativo
            "12345678901",              // cpf
            "Rua das Flores, 123",      // endereco
            "11987654321"               // tel
        );
        socio = socioRepository.save(socio);
        log.info("✅ Sócio criado: {}", socio);

        // 2. Criar Dependentes
        log.info("\n2️⃣ Criando Dependentes...");
        Dependente dependente1 = new Dependente(
            1002L,                      // numInscricao
            "Maria Silva",              // nome
            LocalDate.of(2010, 3, 20),  // dtNascimento
            "Feminino",                 // sexo
            true,                       // ativo
            socio                       // socio (relacionamento)
        );
        dependente1 = dependenteRepository.save(dependente1);
        log.info("✅ Dependente 1 criado: {}", dependente1);

        Dependente dependente2 = new Dependente(
            1003L,                      // numInscricao
            "Pedro Silva",              // nome
            LocalDate.of(2012, 8, 10),  // dtNascimento
            "Masculino",                // sexo
            true,                       // ativo
            socio                       // socio (relacionamento)
        );
        dependente2 = dependenteRepository.save(dependente2);
        log.info("✅ Dependente 2 criado: {}", dependente2);

        // 3. Buscar Sócio com Dependentes (JOIN FETCH)
        log.info("\n3️⃣ Buscando Sócio com Dependentes (JOIN FETCH)...");
        Socio socioComDependentes = socioRepository.findByIdWithDependentes(socio.getId()).orElseThrow();
        log.info("✅ Sócio: {}", socioComDependentes.getNome());
        log.info("   Total de dependentes: {}", socioComDependentes.getDependentes().size());
        socioComDependentes.getDependentes().forEach(dep ->
            log.info("   - Dependente: {} (ID: {})", dep.getNome(), dep.getId())
        );

        // 4. Buscar todos os Dependentes com Sócio (JOIN FETCH)
        log.info("\n4️⃣ Buscando todos os Dependentes com Sócio...");
        dependenteRepository.findAllWithSocio().forEach(dep ->
            log.info("✅ Dependente: {} → Sócio: {}", dep.getNome(), dep.getSocio().getNome())
        );

        // 5. Demonstrar CASCADE e OrphanRemoval
        log.info("\n5️⃣ Testando CASCADE e OrphanRemoval...");
        log.info("   Removendo dependente da lista do sócio...");
        socioComDependentes.removerDependente(dependente2);
        socioRepository.save(socioComDependentes);
        log.info("✅ Dependente removido (orphanRemoval=true irá deletar do banco)");

        // 6. Verificar estrutura do banco (herança JOINED)
        log.info("\n6️⃣ Estrutura do Banco (Herança JOINED):");
        log.info("   📊 Tabela CLIENTE: Contém dados comuns (id, numInscricao, nome, etc)");
        log.info("   📊 Tabela SOCIO: Contém dados específicos (cpf, endereco, tel) + FK para cliente");
        log.info("   📊 Tabela DEPENDENTE: Contém FK para cliente + FK para socio");
        log.info("   ✅ Vantagem: Normalização perfeita, sem duplicação de dados!");

        log.info("\n========================================");
        log.info("✅ Exemplo concluído com sucesso!");
        log.info("========================================");
    }
}

