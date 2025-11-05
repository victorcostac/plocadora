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
 * AtualizarItemApiModel
 */

@JsonTypeName("AtualizarItem")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AtualizarItemApiModel {

  private Integer numSerie;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtAquisicao;

  private String tipoItem;

  private Integer tituloId;

  public AtualizarItemApiModel numSerie(Integer numSerie) {
    this.numSerie = numSerie;
    return this;
  }

  /**
   * Get numSerie
   * @return numSerie
  */
  
  @Schema(name = "num_serie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("num_serie")
  public Integer getNumSerie() {
    return numSerie;
  }

  public void setNumSerie(Integer numSerie) {
    this.numSerie = numSerie;
  }

  public AtualizarItemApiModel dtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
    return this;
  }

  /**
   * Get dtAquisicao
   * @return dtAquisicao
  */
  @Valid 
  @Schema(name = "dt_aquisicao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dt_aquisicao")
  public LocalDate getDtAquisicao() {
    return dtAquisicao;
  }

  public void setDtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
  }

  public AtualizarItemApiModel tipoItem(String tipoItem) {
    this.tipoItem = tipoItem;
    return this;
  }

  /**
   * Get tipoItem
   * @return tipoItem
  */
  
  @Schema(name = "tipo_item", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tipo_item")
  public String getTipoItem() {
    return tipoItem;
  }

  public void setTipoItem(String tipoItem) {
    this.tipoItem = tipoItem;
  }

  public AtualizarItemApiModel tituloId(Integer tituloId) {
    this.tituloId = tituloId;
    return this;
  }

  /**
   * Get tituloId
   * @return tituloId
  */
  
  @Schema(name = "titulo_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("titulo_id")
  public Integer getTituloId() {
    return tituloId;
  }

  public void setTituloId(Integer tituloId) {
    this.tituloId = tituloId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AtualizarItemApiModel atualizarItem = (AtualizarItemApiModel) o;
    return Objects.equals(this.numSerie, atualizarItem.numSerie) &&
        Objects.equals(this.dtAquisicao, atualizarItem.dtAquisicao) &&
        Objects.equals(this.tipoItem, atualizarItem.tipoItem) &&
        Objects.equals(this.tituloId, atualizarItem.tituloId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numSerie, dtAquisicao, tipoItem, tituloId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AtualizarItemApiModel {\n");
    sb.append("    numSerie: ").append(toIndentedString(numSerie)).append("\n");
    sb.append("    dtAquisicao: ").append(toIndentedString(dtAquisicao)).append("\n");
    sb.append("    tipoItem: ").append(toIndentedString(tipoItem)).append("\n");
    sb.append("    tituloId: ").append(toIndentedString(tituloId)).append("\n");
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

