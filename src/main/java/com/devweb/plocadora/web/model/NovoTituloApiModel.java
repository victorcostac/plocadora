package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * NovoTituloApiModel
 */

@JsonTypeName("NovoTitulo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class NovoTituloApiModel {

  private String nome;

  private BigDecimal ano;

  private String sinopse;

  private String categoria;

  private BigDecimal idClasse;

  private BigDecimal idDiretor;

  private BigDecimal idAtor;

  public NovoTituloApiModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public NovoTituloApiModel(String nome, BigDecimal ano, String sinopse, String categoria) {
    this.nome = nome;
    this.ano = ano;
    this.sinopse = sinopse;
    this.categoria = categoria;
  }

  public NovoTituloApiModel nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Get nome
   * @return nome
  */
  @NotNull 
  @Schema(name = "nome", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public NovoTituloApiModel ano(BigDecimal ano) {
    this.ano = ano;
    return this;
  }

  /**
   * Get ano
   * @return ano
  */
  @NotNull @Valid 
  @Schema(name = "ano", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ano")
  public BigDecimal getAno() {
    return ano;
  }

  public void setAno(BigDecimal ano) {
    this.ano = ano;
  }

  public NovoTituloApiModel sinopse(String sinopse) {
    this.sinopse = sinopse;
    return this;
  }

  /**
   * Get sinopse
   * @return sinopse
  */
  @NotNull 
  @Schema(name = "sinopse", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sinopse")
  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  public NovoTituloApiModel categoria(String categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Get categoria
   * @return categoria
  */
  @NotNull 
  @Schema(name = "categoria", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categoria")
  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public NovoTituloApiModel idClasse(BigDecimal idClasse) {
    this.idClasse = idClasse;
    return this;
  }

  /**
   * Get idClasse
   * @return idClasse
  */
  @Valid 
  @Schema(name = "id_classe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_classe")
  public BigDecimal getIdClasse() {
    return idClasse;
  }

  public void setIdClasse(BigDecimal idClasse) {
    this.idClasse = idClasse;
  }

  public NovoTituloApiModel idDiretor(BigDecimal idDiretor) {
    this.idDiretor = idDiretor;
    return this;
  }

  /**
   * Get idDiretor
   * @return idDiretor
  */
  @Valid 
  @Schema(name = "id_diretor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_diretor")
  public BigDecimal getIdDiretor() {
    return idDiretor;
  }

  public void setIdDiretor(BigDecimal idDiretor) {
    this.idDiretor = idDiretor;
  }

  public NovoTituloApiModel idAtor(BigDecimal idAtor) {
    this.idAtor = idAtor;
    return this;
  }

  /**
   * Get idAtor
   * @return idAtor
  */
  @Valid 
  @Schema(name = "id_ator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_ator")
  public BigDecimal getIdAtor() {
    return idAtor;
  }

  public void setIdAtor(BigDecimal idAtor) {
    this.idAtor = idAtor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NovoTituloApiModel novoTitulo = (NovoTituloApiModel) o;
    return Objects.equals(this.nome, novoTitulo.nome) &&
        Objects.equals(this.ano, novoTitulo.ano) &&
        Objects.equals(this.sinopse, novoTitulo.sinopse) &&
        Objects.equals(this.categoria, novoTitulo.categoria) &&
        Objects.equals(this.idClasse, novoTitulo.idClasse) &&
        Objects.equals(this.idDiretor, novoTitulo.idDiretor) &&
        Objects.equals(this.idAtor, novoTitulo.idAtor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, ano, sinopse, categoria, idClasse, idDiretor, idAtor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NovoTituloApiModel {\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    ano: ").append(toIndentedString(ano)).append("\n");
    sb.append("    sinopse: ").append(toIndentedString(sinopse)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    idClasse: ").append(toIndentedString(idClasse)).append("\n");
    sb.append("    idDiretor: ").append(toIndentedString(idDiretor)).append("\n");
    sb.append("    idAtor: ").append(toIndentedString(idAtor)).append("\n");
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

