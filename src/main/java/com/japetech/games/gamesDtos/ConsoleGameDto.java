package com.japetech.games.gamesDtos;

import com.japetech.games.models.ConsoleModel;
import com.japetech.games.models.GameModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsoleGameDto {

    @NotNull(message = "A data de Lançamento é  obrigatória")
    private LocalDate dataLancamento;

    @NotNull(message = "O valor do game é obrigatório")
    private Float valor;

    @NotNull
    private Long idConsole;

    @NotNull
    private Long idGame;

}
