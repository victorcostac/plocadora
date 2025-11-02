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
 * ClasseApiModel
 */

@JsonTypeName("Classe")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ClasseApiModel {

  private Integer id;

  private String nome;

  private Double valor;

  private String prazoDevolucao;

  public ClasseApiModel id(Integer id) {
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

  public ClasseApiModel nome(String nome) {
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

  public ClasseApiModel valor(Double valor) {
    this.valor = valor;
    return this;
  }

  /**
   * Get valor
   * @return valor
  */
  
  @Schema(name = "valor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valor")
  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public ClasseApiModel prazoDevolucao(String prazoDevolucao) {
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
    ClasseApiModel classe = (ClasseApiModel) o;
    return Objects.equals(this.id, classe.id) &&
        Objects.equals(this.nome, classe.nome) &&
        Objects.equals(this.valor, classe.valor) &&
        Objects.equals(this.prazoDevolucao, classe.prazoDevolucao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, valor, prazoDevolucao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClasseApiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

