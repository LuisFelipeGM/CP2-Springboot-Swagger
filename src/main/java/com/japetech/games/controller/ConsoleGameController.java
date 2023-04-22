package com.japetech.games.controller;

import com.japetech.games.gamesDtos.ConsoleGameDto;
import com.japetech.games.models.ConsoleGameModel;
import com.japetech.games.services.ConsoleGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Console Game", description = "API para gerenciamento de consoles games")
@RestController
@RequestMapping("/consolegame")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConsoleGameController {

    final ConsoleGameService consoleGameService;

    public ConsoleGameController(ConsoleGameService consoleGameService){
        this.consoleGameService = consoleGameService;
    }

    @Operation(summary = "Lista todos os console games", description = "Lista todos os console games")
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(consoleGameService.getAll());
    }

    @Operation(summary = "Recupera um console game pelo ID", description = "Recupera os dados de um console game a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Console Game encontrado com sucesso", content = @Content(schema = @Schema(implementation = ConsoleGameModel.class)))
    @ApiResponse(responseCode = "404", description = "Console Game não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ConsoleGameModel> getGameById(@PathVariable Long id){
        Optional<ConsoleGameModel> optionalGame = Optional.ofNullable(consoleGameService.findById(id));

        if(optionalGame.isPresent()){
            return ResponseEntity.ok(optionalGame.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva o console game", description = "Salva o console game")
    @ApiResponse(responseCode = "201", description = "Console Game salva com sucesso", content = @Content(schema = @Schema(implementation = ConsoleGameModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> saveGame(@Valid @RequestBody ConsoleGameDto consoleGameDto){

        ConsoleGameModel consoleGameModel = new ConsoleGameModel();
        BeanUtils.copyProperties(consoleGameDto, consoleGameModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(consoleGameService.save(consoleGameModel));
    }

    @Operation(summary = "Exclui um console game pelo ID", description = "Exclui um console game a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Console Game excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        consoleGameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
