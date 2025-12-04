package com.devweb.plocadora.services;

import com.devweb.plocadora.domain.Locacao;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface de serviço para operações de Locação.
 */
public interface ILocacaoService {

    /**
     * Cria uma nova locação.
     *
     * @param dtLocacao           data e hora da locação
     * @param dtDevolucaoPrevista data e hora prevista para devolução
     * @param valorCobrado        valor cobrado pela locação
     * @param itemId              ID do item locado
     * @param clienteId           ID do cliente que está locando
     * @return Locacao criada
     * @throws IllegalArgumentException  se os dados forem inválidos
     * @throws ResourceNotFoundException se item ou cliente não existirem
     */
    Locacao createLocacao(LocalDateTime dtLocacao, LocalDateTime dtDevolucaoPrevista,
            Double valorCobrado, Long itemId, Long clienteId);

    /**
     * Busca uma locação por ID.
     *
     * @param id ID da locação
     * @return Locacao encontrada
     * @throws ResourceNotFoundException se a locação não existir
     */
    Locacao getLocacao(Long id);

    /**
     * Lista todas as locações.
     *
     * @return lista de locações
     */
    List<Locacao> getLocacoes();

    /**
     * Atualiza uma locação com a devolução efetiva.
     * Calcula multa automaticamente baseado nos dias de atraso.
     *
     * @param id                 ID da locação
     * @param dtDevolucaoEfetiva data e hora da devolução efetiva
     * @return Locacao atualizada com multa calculada
     * @throws ResourceNotFoundException se a locação não existir
     * @throws IllegalStateException     se a locação já foi finalizada
     */
    Locacao finalizarLocacao(Long id, LocalDateTime dtDevolucaoEfetiva);

    /**
     * Deleta uma locação.
     *
     * @param id ID da locação
     * @throws ResourceNotFoundException se a locação não existir
     */
    void deleteLocacao(Long id);

    /**
     * Busca todas as locações de um cliente específico.
     *
     * @param clienteId ID do cliente (Sócio ou Dependente)
     * @return lista de locações do cliente
     * @throws ResourceNotFoundException se o cliente não existir
     */
    List<Locacao> getLocacoesByCliente(Long clienteId);
}
