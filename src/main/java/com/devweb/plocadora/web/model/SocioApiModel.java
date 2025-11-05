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
 * SocioApiModel
 */

@JsonTypeName("Socio")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SocioApiModel {

  private Integer id;

  private Integer numInscricao;

  private String nome;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dtNascimento;

  private String sexo;

  private Boolean ativo;

  private String cpf;

  private String tel;

  private String endereco;

  public SocioApiModel id(Integer id) {
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

  public SocioApiModel numInscricao(Integer numInscricao) {
    this.numInscricao = numInscricao;
    return this;
  }

  /**
   * Get numInscricao
   * @return numInscricao
  */
  
  @Schema(name = "num_inscricao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("num_inscricao")
  public Integer getNumInscricao() {
    return numInscricao;
  }

  public void setNumInscricao(Integer numInscricao) {
    this.numInscricao = numInscricao;
  }

  public SocioApiModel nome(String nome) {
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

  public SocioApiModel dtNascimento(LocalDate dtNascimento) {
    this.dtNascimento = dtNascimento;
    return this;
  }

  /**
   * Get dtNascimento
   * @return dtNascimento
  */
  @Valid 
  @Schema(name = "dt_nascimento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dt_nascimento")
  public LocalDate getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(LocalDate dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public SocioApiModel sexo(String sexo) {
    this.sexo = sexo;
    return this;
  }

  /**
   * Get sexo
   * @return sexo
  */
  
  @Schema(name = "sexo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sexo")
  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public SocioApiModel ativo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

  /**
   * Get ativo
   * @return ativo
  */
  
  @Schema(name = "ativo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ativo")
  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public SocioApiModel cpf(String cpf) {
    this.cpf = cpf;
    return this;
  }

  /**
   * Get cpf
   * @return cpf
  */
  
  @Schema(name = "cpf", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cpf")
  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public SocioApiModel tel(String tel) {
    this.tel = tel;
    return this;
  }

  /**
   * Get tel
   * @return tel
  */
  
  @Schema(name = "tel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tel")
  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public SocioApiModel endereco(String endereco) {
    this.endereco = endereco;
    return this;
  }

  /**
   * Get endereco
   * @return endereco
  */
  
  @Schema(name = "endereco", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endereco")
  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SocioApiModel socio = (SocioApiModel) o;
    return Objects.equals(this.id, socio.id) &&
        Objects.equals(this.numInscricao, socio.numInscricao) &&
        Objects.equals(this.nome, socio.nome) &&
        Objects.equals(this.dtNascimento, socio.dtNascimento) &&
        Objects.equals(this.sexo, socio.sexo) &&
        Objects.equals(this.ativo, socio.ativo) &&
        Objects.equals(this.cpf, socio.cpf) &&
        Objects.equals(this.tel, socio.tel) &&
        Objects.equals(this.endereco, socio.endereco);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numInscricao, nome, dtNascimento, sexo, ativo, cpf, tel, endereco);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SocioApiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numInscricao: ").append(toIndentedString(numInscricao)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    dtNascimento: ").append(toIndentedString(dtNascimento)).append("\n");
    sb.append("    sexo: ").append(toIndentedString(sexo)).append("\n");
    sb.append("    ativo: ").append(toIndentedString(ativo)).append("\n");
    sb.append("    cpf: ").append(toIndentedString(cpf)).append("\n");
    sb.append("    tel: ").append(toIndentedString(tel)).append("\n");
    sb.append("    endereco: ").append(toIndentedString(endereco)).append("\n");
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

