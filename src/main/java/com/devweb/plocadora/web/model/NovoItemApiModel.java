package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
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
 * NovoItemApiModel
 */

@JsonTypeName("NovoItem")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class NovoItemApiModel {

  private BigDecimal numSerie;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtAquisicao;

  private String tipoItem;

  private Integer tituloId;

  public NovoItemApiModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public NovoItemApiModel(BigDecimal numSerie, LocalDate dtAquisicao, String tipoItem) {
    this.numSerie = numSerie;
    this.dtAquisicao = dtAquisicao;
    this.tipoItem = tipoItem;
  }

  public NovoItemApiModel numSerie(BigDecimal numSerie) {
    this.numSerie = numSerie;
    return this;
  }

  /**
   * Get numSerie
   * @return numSerie
  */
  @NotNull @Valid 
  @Schema(name = "num_serie", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("num_serie")
  public BigDecimal getNumSerie() {
    return numSerie;
  }

  public void setNumSerie(BigDecimal numSerie) {
    this.numSerie = numSerie;
  }

  public NovoItemApiModel dtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
    return this;
  }

  /**
   * Get dtAquisicao
   * @return dtAquisicao
  */
  @NotNull @Valid 
  @Schema(name = "dt_aquisicao", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dt_aquisicao")
  public LocalDate getDtAquisicao() {
    return dtAquisicao;
  }

  public void setDtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
  }

  public NovoItemApiModel tipoItem(String tipoItem) {
    this.tipoItem = tipoItem;
    return this;
  }

  /**
   * Get tipoItem
   * @return tipoItem
  */
  @NotNull 
  @Schema(name = "tipo_item", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tipo_item")
  public String getTipoItem() {
    return tipoItem;
  }

  public void setTipoItem(String tipoItem) {
    this.tipoItem = tipoItem;
  }

  public NovoItemApiModel tituloId(Integer tituloId) {
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
    NovoItemApiModel novoItem = (NovoItemApiModel) o;
    return Objects.equals(this.numSerie, novoItem.numSerie) &&
        Objects.equals(this.dtAquisicao, novoItem.dtAquisicao) &&
        Objects.equals(this.tipoItem, novoItem.tipoItem) &&
        Objects.equals(this.tituloId, novoItem.tituloId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numSerie, dtAquisicao, tipoItem, tituloId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NovoItemApiModel {\n");
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

