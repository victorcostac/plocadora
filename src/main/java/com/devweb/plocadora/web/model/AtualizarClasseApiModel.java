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
 * AtualizarClasseApiModel
 */

@JsonTypeName("AtualizarClasse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AtualizarClasseApiModel {

  private String nome;

  private Double valor;

  private String prazoDevolucao;

  public AtualizarClasseApiModel nome(String nome) {
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

  public AtualizarClasseApiModel valor(Double valor) {
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

  public AtualizarClasseApiModel prazoDevolucao(String prazoDevolucao) {
    this.prazoDevolucao = prazoDevolucao;
    return this;
  }

  /**
   * Get prazoDevolucao
   * @return prazoDevolucao
  */
  
  @Schema(name = "prazo_devolucao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prazo_devolucao")
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
    AtualizarClasseApiModel atualizarClasse = (AtualizarClasseApiModel) o;
    return Objects.equals(this.nome, atualizarClasse.nome) &&
        Objects.equals(this.valor, atualizarClasse.valor) &&
        Objects.equals(this.prazoDevolucao, atualizarClasse.prazoDevolucao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, valor, prazoDevolucao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AtualizarClasseApiModel {\n");
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

