package com.devweb.plocadora.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.devweb.plocadora.domain.Locacao;
import com.devweb.plocadora.domain.Socio;
import com.devweb.plocadora.services.ILocacaoService;
import com.devweb.plocadora.services.ISocioService;
import com.devweb.plocadora.web.api.SocioApi;
import com.devweb.plocadora.web.model.AtualizarSocioApiModel;
import com.devweb.plocadora.web.model.LocacaoApiModel;
import com.devweb.plocadora.web.model.NovoSocioApiModel;
import com.devweb.plocadora.web.model.SocioApiModel;
import com.devweb.plocadora.web.model.SocioCriadoApiModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SocioController implements SocioApi {

    private final ISocioService socioService;
    private final ILocacaoService locacaoService;

    @Override
    public ResponseEntity<Void> deleteSocioSocioId(String socioId) {
        try {
            Long id = Long.parseLong(socioId);
            boolean deleted = socioService.deleteSocio(id);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<SocioApiModel>> getSocio() {
        try {
            List<Socio> socios = socioService.getSocios();
            List<SocioApiModel> response = socios.stream()
                    .map(this::mapToSocioApiModel)
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SocioApiModel> getSocioSocioId(String socioId) {
        try {
            Long id = Long.parseLong(socioId);
            Optional<Socio> socioOptional = socioService.getSocioWithDependentes(id);

            if (socioOptional.isPresent()) {
                SocioApiModel response = mapToSocioApiModel(socioOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SocioCriadoApiModel> postSocio(@Valid NovoSocioApiModel novoSocioApiModel) {
        try {
            if (novoSocioApiModel == null || novoSocioApiModel.getNome() == null ||
                    novoSocioApiModel.getCpf() == null || novoSocioApiModel.getNumInscricao() == null) {
                return ResponseEntity.badRequest().build();
            }

            Socio socio = socioService.createSocio(
                    novoSocioApiModel.getNumInscricao().longValue(),
                    novoSocioApiModel.getNome(),
                    novoSocioApiModel.getDtNascimento(),
                    novoSocioApiModel.getSexo(),
                    novoSocioApiModel.getAtivo(),
                    novoSocioApiModel.getCpf(),
                    novoSocioApiModel.getEndereco(),
                    novoSocioApiModel.getTel());

            SocioCriadoApiModel response = mapToSocioCriadoApiModel(socio);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SocioApiModel> putSocioSocioId(String socioId,
            @Valid AtualizarSocioApiModel atualizarSocioApiModel) {
        try {
            if (atualizarSocioApiModel == null || atualizarSocioApiModel.getNome() == null) {
                return ResponseEntity.badRequest().build();
            }

            Long id = Long.parseLong(socioId);
            Optional<Socio> socioOptional = socioService.updateSocio(
                    id,
                    atualizarSocioApiModel.getNome(),
                    atualizarSocioApiModel.getDtNascimento(),
                    atualizarSocioApiModel.getSexo(),
                    atualizarSocioApiModel.getAtivo(),
                    atualizarSocioApiModel.getCpf(),
                    atualizarSocioApiModel.getEndereco(),
                    atualizarSocioApiModel.getTel());

            if (socioOptional.isPresent()) {
                SocioApiModel response = mapToSocioApiModel(socioOptional.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private SocioApiModel mapToSocioApiModel(Socio socio) {
        SocioApiModel model = new SocioApiModel();
        model.setId(socio.getId().intValue());
        model.setNumInscricao(socio.getNumInscricao().intValue());
        model.setNome(socio.getNome());
        model.setDtNascimento(socio.getDtNascimento());
        model.setSexo(socio.getSexo());
        model.setAtivo(socio.getAtivo());
        model.setCpf(socio.getCpf());
        model.setEndereco(socio.getEndereco());
        model.setTel(socio.getTel());
        return model;
    }

    private SocioCriadoApiModel mapToSocioCriadoApiModel(Socio socio) {
        SocioCriadoApiModel model = new SocioCriadoApiModel();
        model.setId(socio.getId().intValue());
        model.setNumInscricao(socio.getNumInscricao().intValue());
        model.setNome(socio.getNome());
        model.setDtNascimento(socio.getDtNascimento());
        model.setSexo(socio.getSexo());
        model.setAtivo(socio.getAtivo());
        model.setCpf(socio.getCpf());
        model.setEndereco(socio.getEndereco());
        model.setTel(socio.getTel());
        return model;
    }

    @Override
    public ResponseEntity<List<LocacaoApiModel>> getLocacoesBySocio(String socioId) {
        Long clienteId = Long.parseLong(socioId);

        // Busca locações do cliente (service valida se existe)
        List<Locacao> locacoes = locacaoService.getLocacoesByCliente(clienteId);

        // Mapeia para API model
        List<LocacaoApiModel> response = locacoes.stream()
                .map(this::mapToLocacaoApiModel)
                .toList();

        return ResponseEntity.ok(response);
    }

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
            model.setIdSocio(locacao.getCliente().getId().intValue());
        }

        return model;
    }

}
