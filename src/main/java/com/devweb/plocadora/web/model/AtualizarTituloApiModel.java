package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AtualizarTituloApiModel
 */

@JsonTypeName("AtualizarTitulo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AtualizarTituloApiModel {

  private String nome;

  private Integer ano;

  private String sinopse;

  private String categoria;

  private Integer idClasse;

  private Integer idDiretor;

  @Valid
  private List<Integer> idAtores;

  public AtualizarTituloApiModel nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Get nome
   * @return nome
  */
  
  @Schema(name = "nome", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public AtualizarTituloApiModel ano(Integer ano) {
    this.ano = ano;
    return this;
  }

  /**
   * Get ano
   * @return ano
  */
  
  @Schema(name = "ano", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ano")
  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public AtualizarTituloApiModel sinopse(String sinopse) {
    this.sinopse = sinopse;
    return this;
  }

  /**
   * Get sinopse
   * @return sinopse
  */
  
  @Schema(name = "sinopse", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sinopse")
  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  public AtualizarTituloApiModel categoria(String categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Get categoria
   * @return categoria
  */
  
  @Schema(name = "categoria", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoria")
  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public AtualizarTituloApiModel idClasse(Integer idClasse) {
    this.idClasse = idClasse;
    return this;
  }

  /**
   * Get idClasse
   * @return idClasse
  */
  
  @Schema(name = "id_classe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_classe")
  public Integer getIdClasse() {
    return idClasse;
  }

  public void setIdClasse(Integer idClasse) {
    this.idClasse = idClasse;
  }

  public AtualizarTituloApiModel idDiretor(Integer idDiretor) {
    this.idDiretor = idDiretor;
    return this;
  }

  /**
   * Get idDiretor
   * @return idDiretor
  */
  
  @Schema(name = "id_diretor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_diretor")
  public Integer getIdDiretor() {
    return idDiretor;
  }

  public void setIdDiretor(Integer idDiretor) {
    this.idDiretor = idDiretor;
  }

  public AtualizarTituloApiModel idAtores(List<Integer> idAtores) {
    this.idAtores = idAtores;
    return this;
  }

  public AtualizarTituloApiModel addIdAtoresItem(Integer idAtoresItem) {
    if (this.idAtores == null) {
      this.idAtores = new ArrayList<>();
    }
    this.idAtores.add(idAtoresItem);
    return this;
  }

  /**
   * Get idAtores
   * @return idAtores
  */
  
  @Schema(name = "id_atores", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_atores")
  public List<Integer> getIdAtores() {
    return idAtores;
  }

  public void setIdAtores(List<Integer> idAtores) {
    this.idAtores = idAtores;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AtualizarTituloApiModel atualizarTitulo = (AtualizarTituloApiModel) o;
    return Objects.equals(this.nome, atualizarTitulo.nome) &&
        Objects.equals(this.ano, atualizarTitulo.ano) &&
        Objects.equals(this.sinopse, atualizarTitulo.sinopse) &&
        Objects.equals(this.categoria, atualizarTitulo.categoria) &&
        Objects.equals(this.idClasse, atualizarTitulo.idClasse) &&
        Objects.equals(this.idDiretor, atualizarTitulo.idDiretor) &&
        Objects.equals(this.idAtores, atualizarTitulo.idAtores);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, ano, sinopse, categoria, idClasse, idDiretor, idAtores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AtualizarTituloApiModel {\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    ano: ").append(toIndentedString(ano)).append("\n");
    sb.append("    sinopse: ").append(toIndentedString(sinopse)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    idClasse: ").append(toIndentedString(idClasse)).append("\n");
    sb.append("    idDiretor: ").append(toIndentedString(idDiretor)).append("\n");
    sb.append("    idAtores: ").append(toIndentedString(idAtores)).append("\n");
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

