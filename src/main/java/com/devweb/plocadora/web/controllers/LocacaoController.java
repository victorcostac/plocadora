package com.devweb.plocadora.web.controllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devweb.plocadora.domain.Locacao;
import com.devweb.plocadora.services.ILocacaoService;
import com.devweb.plocadora.web.api.LocacaoApi;
import com.devweb.plocadora.web.model.AtualizarLocacaoApiModel;
import com.devweb.plocadora.web.model.LocacaoApiModel;
import com.devweb.plocadora.web.model.LocacaoCriadaApiModel;
import com.devweb.plocadora.web.model.NovaLocacaoApiModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller REST para operações de Locação.
 * Implementa CRUD completo com design orientado a exceções.
 * As exceções são interceptadas pelo GlobalExceptionHandler.
 */
@RestController
@RequiredArgsConstructor
public class LocacaoController implements LocacaoApi {

    private final ILocacaoService locacaoService;

    @Override
    public ResponseEntity<LocacaoCriadaApiModel> postLocacao(@Valid NovaLocacaoApiModel novaLocacaoApiModel) {
        // Validações defensivas
        if (novaLocacaoApiModel.getDtLocacao() == null) {
            throw new IllegalArgumentException("Data de locação (dt_locacao) não pode ser nula");
        }
        if (novaLocacaoApiModel.getDtDevolucaoPrevista() == null) {
            throw new IllegalArgumentException("Data de devolução prevista (dt_devolucao_prevista) não pode ser nula");
        }
        if (novaLocacaoApiModel.getValorCobrado() == null) {
            throw new IllegalArgumentException("Valor cobrado (valor_cobrado) não pode ser nulo");
        }
        if (novaLocacaoApiModel.getIdItem() == null) {
            throw new IllegalArgumentException("ID do item (id_item) não pode ser nulo");
        }
        if (novaLocacaoApiModel.getIdCliente() == null) {
            throw new IllegalArgumentException("ID do cliente (id_cliente) não pode ser nulo");
        }

        // Converte LocalDate para LocalDateTime (início do dia)
        LocalDateTime dtLocacao = novaLocacaoApiModel.getDtLocacao().atStartOfDay();
        LocalDateTime dtDevolucaoPrevista = novaLocacaoApiModel.getDtDevolucaoPrevista().atStartOfDay();

        // Criar locação (exceções serão propagadas para GlobalExceptionHandler)
        Locacao locacao = locacaoService.createLocacao(
                dtLocacao,
                dtDevolucaoPrevista,
                novaLocacaoApiModel.getValorCobrado(),
                novaLocacaoApiModel.getIdItem().longValue(),
                novaLocacaoApiModel.getIdCliente().longValue());

        // Mapear resposta
        LocacaoCriadaApiModel response = mapToLocacaoCriadaApiModel(locacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<LocacaoApiModel>> getLocacao() {
        List<Locacao> locacoes = locacaoService.getLocacoes();

        List<LocacaoApiModel> response = locacoes.stream()
                .map(this::mapToLocacaoApiModel)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LocacaoApiModel> getLocacaoById(String locacaoId) {
        Long id = Long.parseLong(locacaoId);
        Locacao locacao = locacaoService.getLocacao(id);

        LocacaoApiModel response = mapToLocacaoApiModel(locacao);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LocacaoApiModel> putLocacaoById(String locacaoId,
            @Valid AtualizarLocacaoApiModel atualizarLocacaoApiModel) {

        Long id = Long.parseLong(locacaoId);

        // Validação defensiva
        if (atualizarLocacaoApiModel.getDtDevolucaoEfetiva() == null) {
            throw new IllegalArgumentException("Data de devolução efetiva (dt_devolucao_efetiva) não pode ser nula");
        }

        // Converte LocalDate para LocalDateTime com hora atual
        LocalDateTime dtDevolucaoEfetiva = atualizarLocacaoApiModel.getDtDevolucaoEfetiva().atTime(LocalTime.now());

        // Finalizar locação (calcula multa automaticamente)
        Locacao locacao = locacaoService.finalizarLocacao(id, dtDevolucaoEfetiva);

        LocacaoApiModel response = mapToLocacaoApiModel(locacao);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteLocacaoById(String locacaoId) {
        Long id = Long.parseLong(locacaoId);
        locacaoService.deleteLocacao(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Mapeia Locacao para LocacaoApiModel.
     */
    private LocacaoApiModel mapToLocacaoApiModel(Locacao locacao) {
        LocacaoApiModel model = new LocacaoApiModel();
        model.setId(locacao.getId().intValue());
        model.setDtLocacao(locacao.getDtLocacao().toLocalDate());
        model.setDtDevolucaoPrevista(locacao.getDtDevolucaoPrevista().toLocalDate());

        if (locacao.getDtDevolucaoEfetiva() != null) {
            model.setDtDevolucaoEfetiva(locacao.getDtDevolucaoEfetiva().toLocalDate());
        }

        model.setValorCobrado(locacao.getValorCobrado());
        model.setMultaCobrado(locacao.getMultaCobrada() != null ? locacao.getMultaCobrada() : 0.0);

        // IDs de Item e Cliente
        if (locacao.getItem() != null) {
            model.setIdItem(locacao.getItem().getId().intValue());
        }
        if (locacao.getCliente() != null) {
            model.setIdCliente(locacao.getCliente().getId().intValue());
        }

        return model;
    }

    /**
     * Mapeia Locacao para LocacaoCriadaApiModel.
     */
    private LocacaoCriadaApiModel mapToLocacaoCriadaApiModel(Locacao locacao) {
        LocacaoCriadaApiModel model = new LocacaoCriadaApiModel();
        model.setId(locacao.getId().intValue());
        model.setDtLocacao(locacao.getDtLocacao().toLocalDate());
        model.setDtDevolucaoPrevista(locacao.getDtDevolucaoPrevista().toLocalDate());
        model.setValorCobrado(locacao.getValorCobrado());
        model.setMultaCobrado(0.0);

        // IDs de Item e Cliente
        if (locacao.getItem() != null) {
            model.setIdItem(locacao.getItem().getId().intValue());
        }
        if (locacao.getCliente() != null) {
            model.setIdCliente(locacao.getCliente().getId().intValue());
        }

        return model;
    }
}
