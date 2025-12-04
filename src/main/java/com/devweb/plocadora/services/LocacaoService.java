package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Cliente;
import com.devweb.plocadora.domain.Item;
import com.devweb.plocadora.domain.Locacao;
import com.devweb.plocadora.infrastructure.repositories.ItemJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.LocacaoJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.SocioJpaRepository;
import com.devweb.plocadora.infrastructure.repositories.DependenteJpaRepository;
import com.devweb.plocadora.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Implementação do serviço de Locação.
 * Gerencia operações de CRUD e cálculo de multas por atraso.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LocacaoService implements ILocacaoService {

    private final LocacaoJpaRepository locacaoRepository;
    private final ItemJpaRepository itemRepository;
    private final SocioJpaRepository socioRepository;
    private final DependenteJpaRepository dependenteRepository;

    // Valor da multa por dia de atraso (pode ser configurável)
    private static final double MULTA_POR_DIA = 5.0;

    @Override
    public Locacao createLocacao(LocalDateTime dtLocacao, LocalDateTime dtDevolucaoPrevista,
            Double valorCobrado, Long itemId, Long clienteId) {

        // Validações de entrada
        if (dtLocacao == null) {
            throw new IllegalArgumentException("Data de locação é obrigatória");
        }
        if (dtDevolucaoPrevista == null) {
            throw new IllegalArgumentException("Data de devolução prevista é obrigatória");
        }
        if (valorCobrado == null || valorCobrado <= 0) {
            throw new IllegalArgumentException("Valor cobrado deve ser maior que zero");
        }
        if (dtDevolucaoPrevista.isBefore(dtLocacao)) {
            throw new IllegalArgumentException("Data de devolução prevista não pode ser anterior à data de locação");
        }

        // Buscar item
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", itemId));

        // Buscar cliente (pode ser Socio ou Dependente)
        Cliente cliente = findCliente(clienteId);

        for (Locacao locacao : cliente.getLocacoes()) {
            if (locacao.getDtDevolucaoEfetiva() == null
                    && locacao.getDtDevolucaoPrevista().isBefore(LocalDateTime.now())) {
                throw new IllegalStateException(
                        "Cliente não pode fazer mais alocações. Deve se regularizar seu débito");
            }
        }

        // Criar locação
        Locacao locacao = new Locacao(dtLocacao, dtDevolucaoPrevista, valorCobrado, item, cliente);

        return locacaoRepository.save(locacao);
    }

    @Override
    @Transactional(readOnly = true)
    public Locacao getLocacao(Long id) {
        return locacaoRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Locacao> getLocacoes() {
        return locacaoRepository.findAllWithRelations();
    }

    @Override
    public Locacao finalizarLocacao(Long id, LocalDateTime dtDevolucaoEfetiva) {
        // Validações
        if (dtDevolucaoEfetiva == null) {
            throw new IllegalArgumentException("Data de devolução efetiva é obrigatória");
        }

        // Buscar locação
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação", id));

        // Verificar se já foi finalizada
        if (locacao.getDtDevolucaoEfetiva() != null) {
            throw new IllegalStateException("Locação já foi finalizada anteriormente");
        }

        // Validar data de devolução
        if (dtDevolucaoEfetiva.isBefore(locacao.getDtLocacao())) {
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de locação");
        }

        // Calcular multa por atraso
        Double multaCobrada = calcularMulta(locacao.getDtDevolucaoPrevista(), dtDevolucaoEfetiva);

        // Atualizar locação
        locacao.AtualizarLocacao(dtDevolucaoEfetiva, multaCobrada);

        return locacaoRepository.save(locacao);
    }

    @Override
    public void deleteLocacao(Long id) {
        if (!locacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Locação", id);
        }
        locacaoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Locacao> getLocacoesByCliente(Long clienteId) {
        // Buscar cliente para validar que existe
        Cliente cliente = findCliente(clienteId);

        // Retornar locações do cliente
        return cliente.getLocacoes();
    }

    /**
     * Calcula a multa baseada nos dias de atraso.
     * 
     * @param dtDevolucaoPrevista data prevista para devolução
     * @param dtDevolucaoEfetiva  data efetiva da devolução
     * @return valor da multa (0.0 se não houver atraso)
     */
    private Double calcularMulta(LocalDateTime dtDevolucaoPrevista, LocalDateTime dtDevolucaoEfetiva) {
        // Se devolveu antes ou no prazo, sem multa
        if (dtDevolucaoEfetiva.isBefore(dtDevolucaoPrevista) ||
                dtDevolucaoEfetiva.isEqual(dtDevolucaoPrevista)) {
            return 0.0;
        }

        // Calcular dias de atraso (arredonda para cima)
        long diasAtraso = ChronoUnit.DAYS.between(dtDevolucaoPrevista, dtDevolucaoEfetiva);

        // Se a diferença for menor que 1 dia mas houver atraso, cobra 1 dia
        if (diasAtraso == 0 && dtDevolucaoEfetiva.isAfter(dtDevolucaoPrevista)) {
            diasAtraso = 1;
        }

        // Calcular multa
        return diasAtraso * MULTA_POR_DIA;
    }

    /**
     * Busca um cliente (Socio ou Dependente) pelo ID.
     * Tenta primeiro como Socio, depois como Dependente.
     * 
     * @param clienteId ID do cliente
     * @return Cliente encontrado
     * @throws ResourceNotFoundException se não encontrar
     */
    private Cliente findCliente(Long clienteId) {
        // Tentar buscar como Socio
        return socioRepository.findById(clienteId)
                .<Cliente>map(socio -> socio)
                .orElseGet(() ->
                // Se não for Socio, tentar como Dependente
                dependenteRepository.findById(clienteId)
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente", clienteId)));
    }
}
