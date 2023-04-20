package com.japetech.games.gamesDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class EmpresaDto {

    @Schema(example = "Sony")
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @Schema(example = "12.345.678/9123-45")
    @NotBlank(message = "O CNPJ é obrigatório")
    private String cnpj;

    @Schema(example = "Sony")
    @NotBlank(message = "O nome fantasia é obrigatório")
    @Size(max = 50, message = "O nome fantasia deve ter no máximo 50 caracteres")
    private String nomeFantasia;

    @Schema(example = "11")
    @NotNull(message = "O DDD é obrigatório")
    private Integer ddd;

    @Schema(example = "974609481")
    @NotNull(message = "O telefone é obrigatório")
    private Integer telefone;

    private EnderecoDto endereco;

}
