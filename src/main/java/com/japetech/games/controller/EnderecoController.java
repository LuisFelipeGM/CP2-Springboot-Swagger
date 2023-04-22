package com.japetech.games.controller;

import com.japetech.games.gamesDtos.EnderecoDto;
import com.japetech.games.models.EmpresaModel;
import com.japetech.games.models.EnderecoModel;
import com.japetech.games.services.EnderecoService;
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

@Tag(name = "Endereco", description = "API para gerenciamento de enderecos")
@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnderecoController {

    final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Lista todas os enderecos", description = "Lista todos os enderecos")
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
    }

    @Operation(summary = "Recupera um endereco pelo ID", description = "Recupera os dados de um endereco a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Endereco encontrado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    @ApiResponse(responseCode = "404", description = "Endereco não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getGameById(@PathVariable Long id){
        Optional<EnderecoModel> optionalEndereco = Optional.ofNullable(enderecoService.findById(id));

        if(optionalEndereco.isPresent()){
            return ResponseEntity.ok(optionalEndereco.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Recupera uma lista de enderecos pelo Estado", description = "Recupera os dados de enderecos a partir do seu Estado")
    @ApiResponse(responseCode = "200", description = "Enderecos encontrados com sucesso", content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    @ApiResponse(responseCode = "404", description = "Enderecos não encontrados")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EnderecoModel>> getGameByestado(@PathVariable String estado){
        Optional<List<EnderecoModel>> optionalEndereco = Optional.ofNullable( enderecoService.findByestadoContainingIgnoreCase(estado));
        return optionalEndereco.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
