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
 * ErroRequisicaoApiModel
 */

@JsonTypeName("ErroRequisicao")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ErroRequisicaoApiModel {

  private String status;

  private String mensagem;

  public ErroRequisicaoApiModel status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "Status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ErroRequisicaoApiModel mensagem(String mensagem) {
    this.mensagem = mensagem;
    return this;
  }

  /**
   * Get mensagem
   * @return mensagem
  */
  
  @Schema(name = "mensagem", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mensagem")
  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErroRequisicaoApiModel erroRequisicao = (ErroRequisicaoApiModel) o;
    return Objects.equals(this.status, erroRequisicao.status) &&
        Objects.equals(this.mensagem, erroRequisicao.mensagem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, mensagem);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErroRequisicaoApiModel {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    mensagem: ").append(toIndentedString(mensagem)).append("\n");
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

