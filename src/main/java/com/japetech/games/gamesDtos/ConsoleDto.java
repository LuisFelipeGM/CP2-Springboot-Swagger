package com.japetech.games.gamesDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConsoleDto {

    @Schema(example = "Playstation 5")
    @NotBlank(message = "O nome do console é obrigatório")
    @Size(max = 50, message = "O nome do console deve ter no máximo 50 caracteres")
    private String nome;

    @NotNull
    private Long idEmpresa;

}
