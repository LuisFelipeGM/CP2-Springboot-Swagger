package com.japetech.games.controller;


import com.japetech.games.gamesDtos.ConsoleDto;
import com.japetech.games.models.ConsoleModel;
import com.japetech.games.services.ConsoleService;
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

@Tag(name = "Console", description = "API para gerenciamento de consoles")
@RestController
@RequestMapping("/console")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConsoleController {

    final ConsoleService consoleService;

    public ConsoleController(ConsoleService consoleService){
        this.consoleService = consoleService;
    }

    @Operation(summary = "Lista todas os consoles", description = "Lista todos os consoles")
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(consoleService.getAll());
    }

    @Operation(summary = "Recupera um console pelo ID", description = "Recupera os dados de um console a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Console encontrado com sucesso", content = @Content(schema = @Schema(implementation = ConsoleModel.class)))
    @ApiResponse(responseCode = "404", description = "Console não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ConsoleModel> getGameById(@PathVariable Long id){
        Optional<ConsoleModel> optionalGame = Optional.ofNullable(consoleService.findById(id));

        if(optionalGame.isPresent()){
            return ResponseEntity.ok(optionalGame.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva um console", description = "Salva um console")
    @ApiResponse(responseCode = "201", description = "Console salva com sucesso", content = @Content(schema = @Schema(implementation = ConsoleModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> saveGame(@Valid @RequestBody ConsoleDto consoleDto){

        ConsoleModel consoleModel = new ConsoleModel();
        BeanUtils.copyProperties(consoleDto, consoleModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(consoleService.save(consoleModel));
    }

    @Operation(summary = "Exclui um console pelo ID", description = "Exclui um console a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Console excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        consoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
