package com.japetech.games.gamesDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class GameDto  {


    @Schema(example = "The Last of Us")
    @NotBlank(message = "O nome do jogo é obrigatório")
    @Size(max = 50, message = "O nome do jogo deve ter no máximo 50 caracteres")
    private String nome;

    @Schema(example = "Naugthy Dog")
    @NotBlank(message = "O nome do criador do jogo é obrigatório")
    @Size(max = 50, message = "O nome do do criador do jogo deve ter no máximo 50 caracteres")
    private String nomeDev;

}
