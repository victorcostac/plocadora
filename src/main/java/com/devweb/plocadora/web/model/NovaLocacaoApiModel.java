package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * NovaLocacaoApiModel
 */

@JsonTypeName("NovaLocacao")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class NovaLocacaoApiModel {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtLocacao;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtDevolucaoPrevista;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtDevolucaoEfetiva;

  private Double valorCobrado;

  private Double multaCobrado;

  private Integer idItem;

  private Integer idSocio;

  public NovaLocacaoApiModel dtLocacao(LocalDate dtLocacao) {
    this.dtLocacao = dtLocacao;
    return this;
  }

  /**
   * Get dtLocacao
   * @return dtLocacao
  */
  @Valid 
  @Schema(name = "dt_locacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dt_locacao")
  public LocalDate getDtLocacao() {
    return dtLocacao;
  }

  public void setDtLocacao(LocalDate dtLocacao) {
    this.dtLocacao = dtLocacao;
  }

  public NovaLocacaoApiModel dtDevolucaoPrevista(LocalDate dtDevolucaoPrevista) {
    this.dtDevolucaoPrevista = dtDevolucaoPrevista;
    return this;
  }

  /**
   * Get dtDevolucaoPrevista
   * @return dtDevolucaoPrevista
  */
  @Valid 
  @Schema(name = "dt_devolucao_prevista", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dt_devolucao_prevista")
  public LocalDate getDtDevolucaoPrevista() {
    return dtDevolucaoPrevista;
  }

  public void setDtDevolucaoPrevista(LocalDate dtDevolucaoPrevista) {
    this.dtDevolucaoPrevista = dtDevolucaoPrevista;
  }

  public NovaLocacaoApiModel dtDevolucaoEfetiva(LocalDate dtDevolucaoEfetiva) {
    this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
    return this;
  }

  /**
   * Get dtDevolucaoEfetiva
   * @return dtDevolucaoEfetiva
  */
  @Valid 
  @Schema(name = "dt_devolucao_efetiva", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dt_devolucao_efetiva")
  public LocalDate getDtDevolucaoEfetiva() {
    return dtDevolucaoEfetiva;
  }

  public void setDtDevolucaoEfetiva(LocalDate dtDevolucaoEfetiva) {
    this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
  }

  public NovaLocacaoApiModel valorCobrado(Double valorCobrado) {
    this.valorCobrado = valorCobrado;
    return this;
  }

  /**
   * Get valorCobrado
   * @return valorCobrado
  */
  
  @Schema(name = "valor_cobrado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valor_cobrado")
  public Double getValorCobrado() {
    return valorCobrado;
  }

  public void setValorCobrado(Double valorCobrado) {
    this.valorCobrado = valorCobrado;
  }

  public NovaLocacaoApiModel multaCobrado(Double multaCobrado) {
    this.multaCobrado = multaCobrado;
    return this;
  }

  /**
   * Get multaCobrado
   * @return multaCobrado
  */
  
  @Schema(name = "multa_cobrado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("multa_cobrado")
  public Double getMultaCobrado() {
    return multaCobrado;
  }

  public void setMultaCobrado(Double multaCobrado) {
    this.multaCobrado = multaCobrado;
  }

  public NovaLocacaoApiModel idItem(Integer idItem) {
    this.idItem = idItem;
    return this;
  }

  /**
   * Get idItem
   * @return idItem
  */
  
  @Schema(name = "id_item", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_item")
  public Integer getIdItem() {
    return idItem;
  }

  public void setIdItem(Integer idItem) {
    this.idItem = idItem;
  }

  public NovaLocacaoApiModel idSocio(Integer idSocio) {
    this.idSocio = idSocio;
    return this;
  }

  /**
   * Get idSocio
   * @return idSocio
  */
  
  @Schema(name = "id_socio", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_socio")
  public Integer getIdSocio() {
    return idSocio;
  }

  public void setIdSocio(Integer idSocio) {
    this.idSocio = idSocio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NovaLocacaoApiModel novaLocacao = (NovaLocacaoApiModel) o;
    return Objects.equals(this.dtLocacao, novaLocacao.dtLocacao) &&
        Objects.equals(this.dtDevolucaoPrevista, novaLocacao.dtDevolucaoPrevista) &&
        Objects.equals(this.dtDevolucaoEfetiva, novaLocacao.dtDevolucaoEfetiva) &&
        Objects.equals(this.valorCobrado, novaLocacao.valorCobrado) &&
        Objects.equals(this.multaCobrado, novaLocacao.multaCobrado) &&
        Objects.equals(this.idItem, novaLocacao.idItem) &&
        Objects.equals(this.idSocio, novaLocacao.idSocio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dtLocacao, dtDevolucaoPrevista, dtDevolucaoEfetiva, valorCobrado, multaCobrado, idItem, idSocio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NovaLocacaoApiModel {\n");
    sb.append("    dtLocacao: ").append(toIndentedString(dtLocacao)).append("\n");
    sb.append("    dtDevolucaoPrevista: ").append(toIndentedString(dtDevolucaoPrevista)).append("\n");
    sb.append("    dtDevolucaoEfetiva: ").append(toIndentedString(dtDevolucaoEfetiva)).append("\n");
    sb.append("    valorCobrado: ").append(toIndentedString(valorCobrado)).append("\n");
    sb.append("    multaCobrado: ").append(toIndentedString(multaCobrado)).append("\n");
    sb.append("    idItem: ").append(toIndentedString(idItem)).append("\n");
    sb.append("    idSocio: ").append(toIndentedString(idSocio)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

