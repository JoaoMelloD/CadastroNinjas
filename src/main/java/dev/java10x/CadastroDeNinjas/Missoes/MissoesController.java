package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Missões", description = "Operações relacionadas ao gerenciamento de missões")
public class MissoesController {

    final private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) { this.missoesService = missoesService; }

    @Operation(
        summary = "Listar todas as missões",
        description = "Retorna uma lista completa com todas as missões cadastradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissoesDTO.class)))
    })
    @GetMapping("/missoes")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @Operation(
        summary = "Buscar missão por ID",
        description = "Retorna os dados de uma missão específica com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissoesDTO.class))),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @GetMapping("/missoes/{id}")
    public ResponseEntity<?> listarMissao(
            @Parameter(description = "ID da missão a ser buscada", required = true, example = "1")
            @PathVariable Long id) {
        MissoesDTO missoesDTO = missoesService.listarMissao(id);
        if (missoesDTO != null) {
            return ResponseEntity.ok(missoesDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja Não Encontrado");
    }

    @Operation(
        summary = "Criar uma nova missão",
        description = "Cadastra uma nova missão no sistema com nome e nível de dificuldade"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Missão criada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissoesDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping("/missoes")
    public ResponseEntity<?> criarMissao(@RequestBody MissoesDTO missao) {
        MissoesDTO missoesDTO = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missoesDTO);
    }

    @Operation(
        summary = "Atualizar dados de uma missão",
        description = "Atualiza as informações de uma missão existente com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissoesDTO.class))),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @PutMapping("/missoes/{id}")
    public ResponseEntity<?> atualizarMissao(
            @RequestBody MissoesDTO missoesModel,
            @Parameter(description = "ID da missão a ser atualizada", required = true, example = "1")
            @PathVariable Long id) {
        MissoesDTO missoesDTO = missoesService.atualizar(missoesModel, id);
        if (missoesDTO != null) {
            return ResponseEntity.ok(missoesDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missão Não Encontrada");
    }

    @Operation(
        summary = "Deletar uma missão",
        description = "Remove permanentemente uma missão do sistema com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @DeleteMapping("/missoes/{id}")
    public ResponseEntity<?> deletarMissao(
            @Parameter(description = "ID da missão a ser deletada", required = true, example = "1")
            @PathVariable Long id) {
        if (missoesService.listarMissao(id) != null) {
            missoesService.deletar(id);
            return ResponseEntity.ok().body("Missão Deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missão não encontrada");
    }
}
