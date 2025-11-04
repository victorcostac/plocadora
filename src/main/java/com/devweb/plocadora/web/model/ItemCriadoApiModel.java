package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.devweb.plocadora.web.model.TituloApiModel;
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
 * ItemCriadoApiModel
 */

@JsonTypeName("ItemCriado")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ItemCriadoApiModel {

  private Integer id;

  private String numSerie;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtAquisicao;

  private String tipoItem;

  private TituloApiModel titulo;

  public ItemCriadoApiModel id(Integer id) {
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

  public ItemCriadoApiModel numSerie(String numSerie) {
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

  public ItemCriadoApiModel dtAquisicao(LocalDate dtAquisicao) {
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

  public ItemCriadoApiModel tipoItem(String tipoItem) {
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

  public ItemCriadoApiModel titulo(TituloApiModel titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
  */
  @Valid 
  @Schema(name = "titulo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("titulo")
  public TituloApiModel getTitulo() {
    return titulo;
  }

  public void setTitulo(TituloApiModel titulo) {
    this.titulo = titulo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemCriadoApiModel itemCriado = (ItemCriadoApiModel) o;
    return Objects.equals(this.id, itemCriado.id) &&
        Objects.equals(this.numSerie, itemCriado.numSerie) &&
        Objects.equals(this.dtAquisicao, itemCriado.dtAquisicao) &&
        Objects.equals(this.tipoItem, itemCriado.tipoItem) &&
        Objects.equals(this.titulo, itemCriado.titulo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numSerie, dtAquisicao, tipoItem, titulo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemCriadoApiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numSerie: ").append(toIndentedString(numSerie)).append("\n");
    sb.append("    dtAquisicao: ").append(toIndentedString(dtAquisicao)).append("\n");
    sb.append("    tipoItem: ").append(toIndentedString(tipoItem)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
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

