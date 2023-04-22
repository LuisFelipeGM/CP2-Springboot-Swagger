package com.japetech.games.controller;

import com.japetech.games.gamesDtos.EmpresaDto;
import com.japetech.games.models.EmpresaModel;
import com.japetech.games.services.EmpresaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Empresa", description = "API para gerenciamento de empresas")
@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmpresaController {

    final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @Operation(summary = "Lista todas as empresas", description = "Lista todas as empresas")
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.getAll());
    }

    @Operation(summary = "Recupera uma empresa pelo ID", description = "Recupera os dados de uma empresa a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Empresa encontrada com sucesso", content = @Content(schema = @Schema(implementation = EmpresaModel.class)))
    @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel> getGameById(@PathVariable Long id){
        Optional<EmpresaModel> optionalEmpresa = Optional.ofNullable(empresaService.findById(id));

        if(optionalEmpresa.isPresent()){
            return ResponseEntity.ok(optionalEmpresa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Recupera uma lista de empresas pelo Nome", description = "Recupera os dados de empresas a partir do seu Nome")
    @ApiResponse(responseCode = "200", description = "Empresas encontradas com sucesso", content = @Content(schema = @Schema(implementation = EmpresaModel.class)))
    @ApiResponse(responseCode = "404", description = "Empresas não encontradas")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EmpresaModel>> getByNome(@PathVariable String nome){
        Optional<List<EmpresaModel>> optionalEmpresa = Optional.ofNullable(empresaService.findBynomeContainingIgnoreCase(nome));
        return optionalEmpresa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva a empresa", description = "Salva a empresa")
    @ApiResponse(responseCode = "201", description = "Empresa salva com sucesso", content = @Content(schema = @Schema(implementation = EmpresaModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> saveGame(@Valid @RequestBody EmpresaDto empresaDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresaDto));
    }

    @Operation(summary = "Exclui uma empresa pelo ID", description = "Exclui uma empresa a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Empresa excluida com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        empresaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}


