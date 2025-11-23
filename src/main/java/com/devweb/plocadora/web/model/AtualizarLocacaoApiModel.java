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
 * AtualizarLocacaoApiModel
 */

@JsonTypeName("AtualizarLocacao")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AtualizarLocacaoApiModel {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtDevolucaoEfetiva;

  private Double multaCobrado;

  public AtualizarLocacaoApiModel dtDevolucaoEfetiva(LocalDate dtDevolucaoEfetiva) {
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

  public AtualizarLocacaoApiModel multaCobrado(Double multaCobrado) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AtualizarLocacaoApiModel atualizarLocacao = (AtualizarLocacaoApiModel) o;
    return Objects.equals(this.dtDevolucaoEfetiva, atualizarLocacao.dtDevolucaoEfetiva) &&
        Objects.equals(this.multaCobrado, atualizarLocacao.multaCobrado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dtDevolucaoEfetiva, multaCobrado);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AtualizarLocacaoApiModel {\n");
    sb.append("    dtDevolucaoEfetiva: ").append(toIndentedString(dtDevolucaoEfetiva)).append("\n");
    sb.append("    multaCobrado: ").append(toIndentedString(multaCobrado)).append("\n");
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

