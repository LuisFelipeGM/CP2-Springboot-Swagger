package com.japetech.games.controller;

import com.japetech.games.gamesDtos.GameDto;
import com.japetech.games.models.GameModel;
import com.japetech.games.services.GameService;
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

import java.util.List;
import java.util.Optional;

@Tag(name = "Game", description = "API para gerenciamento de games")
@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @Operation(summary = "Lista todos os games", description = "Lista todos os games")
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAll());
    }

    @Operation(summary = "Recupera um game pelo ID", description = "Recupera os dados de um game a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Game encontrado com sucesso", content = @Content(schema = @Schema(implementation = GameModel.class)))
    @ApiResponse(responseCode = "404", description = "Game não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<GameModel> getGameById(@PathVariable Long id){
        Optional<GameModel> optionalGame = Optional.ofNullable(gameService.findById(id));

        if(optionalGame.isPresent()){
            return ResponseEntity.ok(optionalGame.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Recupera uma lista de games pelo Nome", description = "Recupera os dados de games a partir do seu Nome")
    @ApiResponse(responseCode = "200", description = "Games encontrados com sucesso", content = @Content(schema = @Schema(implementation = GameModel.class)))
    @ApiResponse(responseCode = "404", description = "Games não encontrados")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<GameModel>> getByNome(@PathVariable String nome){
        Optional<List<GameModel>> optionalGame = Optional.ofNullable(gameService.findBynomeContainingIgnoreCase(nome));
        return optionalGame.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva o game", description = "Salva o game")
    @ApiResponse(responseCode = "201", description = "Game salvo com sucesso", content = @Content(schema = @Schema(implementation = GameModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> saveGame(@Valid @RequestBody GameDto gameDto){

        GameModel gameModel = new GameModel();
        BeanUtils.copyProperties(gameDto, gameModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(gameModel));
    }

    @Operation(summary = "Exclui um game pelo ID", description = "Exclui um game a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Game excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
