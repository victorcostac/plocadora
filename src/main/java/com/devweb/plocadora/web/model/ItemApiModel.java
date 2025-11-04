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
 * ItemApiModel
 */

@JsonTypeName("Item")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ItemApiModel {

  private Integer id;

  private String numSerie;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtAquisicao;

  private String tipoItem;

  public ItemApiModel id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ItemApiModel numSerie(String numSerie) {
    this.numSerie = numSerie;
    return this;
  }

  /**
   * Get numSerie
   * @return numSerie
  */
  
  @Schema(name = "num_serie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("num_serie")
  public String getNumSerie() {
    return numSerie;
  }

  public void setNumSerie(String numSerie) {
    this.numSerie = numSerie;
  }

  public ItemApiModel dtAquisicao(LocalDate dtAquisicao) {
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

  public ItemApiModel tipoItem(String tipoItem) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemApiModel item = (ItemApiModel) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.numSerie, item.numSerie) &&
        Objects.equals(this.dtAquisicao, item.dtAquisicao) &&
        Objects.equals(this.tipoItem, item.tipoItem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numSerie, dtAquisicao, tipoItem);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemApiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numSerie: ").append(toIndentedString(numSerie)).append("\n");
    sb.append("    dtAquisicao: ").append(toIndentedString(dtAquisicao)).append("\n");
    sb.append("    tipoItem: ").append(toIndentedString(tipoItem)).append("\n");
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

