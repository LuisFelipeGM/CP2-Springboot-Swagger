package com.japetech.games.gamesDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoDto  {

    @Schema(example = "Rua Fidêncio Ramos")
    @NotBlank(message = "O logradouro é obrigatório")
    @Size (max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    private String logradouro;

    @Schema(example = "308")
    @NotBlank(message = "O número é obrigatório")
    @Size(max = 10, message = "O logradouro deve ter no máximo 10 caracteres")
    private String numero;

    @Schema(example = "Vila Olímpia")
    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 50, message = "O bairro ter no máximo 50 caracteres")
    private String bairro;

    @Schema(example = "São Paulo")
    @NotBlank(message = "A cidade é obrigatório")
    @Size(max = 50, message = "O cidade ter no máximo 50 caracteres")
    private String cidade;

    @Schema(example = "SP")
    @NotBlank(message = "O estado é obrigatório")
    @Size(max = 2, message = "O estado ter no máximo 2 caracteres")
    private String estado;

    @Schema(example = "04551-902")
    @NotBlank(message = "O cep é obrigatório")
    @Size(message = "O estado ter no máximo 2 caracteres")
    private String cep;
}
