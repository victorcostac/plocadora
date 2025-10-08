package com.devweb.plocadora.web.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ClasseCriadaApiModel
 */

@JsonTypeName("ClasseCriada")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ClasseCriadaApiModel {

  private String nome;

  private String valor;

  private String prazoDevolucao;

  public ClasseCriadaApiModel nome(String nome) {
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

  public ClasseCriadaApiModel valor(String valor) {
    this.valor = valor;
    return this;
  }

  /**
   * Get valor
   * @return valor
  */
  
  @Schema(name = "valor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valor")
  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public ClasseCriadaApiModel prazoDevolucao(String prazoDevolucao) {
    this.prazoDevolucao = prazoDevolucao;
    return this;
  }

  /**
   * Get prazoDevolucao
   * @return prazoDevolucao
  */
  
  @Schema(name = "prazoDevolucao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prazoDevolucao")
  public String getPrazoDevolucao() {
    return prazoDevolucao;
  }

  public void setPrazoDevolucao(String prazoDevolucao) {
    this.prazoDevolucao = prazoDevolucao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClasseCriadaApiModel classeCriada = (ClasseCriadaApiModel) o;
    return Objects.equals(this.nome, classeCriada.nome) &&
        Objects.equals(this.valor, classeCriada.valor) &&
        Objects.equals(this.prazoDevolucao, classeCriada.prazoDevolucao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, valor, prazoDevolucao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClasseCriadaApiModel {\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    valor: ").append(toIndentedString(valor)).append("\n");
    sb.append("    prazoDevolucao: ").append(toIndentedString(prazoDevolucao)).append("\n");
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

