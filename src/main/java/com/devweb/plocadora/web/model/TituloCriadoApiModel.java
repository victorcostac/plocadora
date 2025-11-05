package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.devweb.plocadora.web.model.AtorApiModel;
import com.devweb.plocadora.web.model.ClasseApiModel;
import com.devweb.plocadora.web.model.DiretorApiModel;
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
 * TituloCriadoApiModel
 */

@JsonTypeName("TituloCriado")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TituloCriadoApiModel {

  private Integer id;

  private String nome;

  private Integer ano;

  private String sinopse;

  private String categoria;

  private ClasseApiModel classe;

  private DiretorApiModel diretor;

  @Valid
  private List<@Valid AtorApiModel> atores;

  public TituloCriadoApiModel id(Integer id) {
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

  public TituloCriadoApiModel nome(String nome) {
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

  public TituloCriadoApiModel ano(Integer ano) {
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

  public TituloCriadoApiModel sinopse(String sinopse) {
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

  public TituloCriadoApiModel categoria(String categoria) {
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

  public TituloCriadoApiModel classe(ClasseApiModel classe) {
    this.classe = classe;
    return this;
  }

  /**
   * Get classe
   * @return classe
  */
  @Valid 
  @Schema(name = "classe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("classe")
  public ClasseApiModel getClasse() {
    return classe;
  }

  public void setClasse(ClasseApiModel classe) {
    this.classe = classe;
  }

  public TituloCriadoApiModel diretor(DiretorApiModel diretor) {
    this.diretor = diretor;
    return this;
  }

  /**
   * Get diretor
   * @return diretor
  */
  @Valid 
  @Schema(name = "diretor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("diretor")
  public DiretorApiModel getDiretor() {
    return diretor;
  }

  public void setDiretor(DiretorApiModel diretor) {
    this.diretor = diretor;
  }

  public TituloCriadoApiModel atores(List<@Valid AtorApiModel> atores) {
    this.atores = atores;
    return this;
  }

  public TituloCriadoApiModel addAtoresItem(AtorApiModel atoresItem) {
    if (this.atores == null) {
      this.atores = new ArrayList<>();
    }
    this.atores.add(atoresItem);
    return this;
  }

  /**
   * Get atores
   * @return atores
  */
  @Valid 
  @Schema(name = "atores", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("atores")
  public List<@Valid AtorApiModel> getAtores() {
    return atores;
  }

  public void setAtores(List<@Valid AtorApiModel> atores) {
    this.atores = atores;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TituloCriadoApiModel tituloCriado = (TituloCriadoApiModel) o;
    return Objects.equals(this.id, tituloCriado.id) &&
        Objects.equals(this.nome, tituloCriado.nome) &&
        Objects.equals(this.ano, tituloCriado.ano) &&
        Objects.equals(this.sinopse, tituloCriado.sinopse) &&
        Objects.equals(this.categoria, tituloCriado.categoria) &&
        Objects.equals(this.classe, tituloCriado.classe) &&
        Objects.equals(this.diretor, tituloCriado.diretor) &&
        Objects.equals(this.atores, tituloCriado.atores);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, ano, sinopse, categoria, classe, diretor, atores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TituloCriadoApiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    ano: ").append(toIndentedString(ano)).append("\n");
    sb.append("    sinopse: ").append(toIndentedString(sinopse)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    classe: ").append(toIndentedString(classe)).append("\n");
    sb.append("    diretor: ").append(toIndentedString(diretor)).append("\n");
    sb.append("    atores: ").append(toIndentedString(atores)).append("\n");
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

