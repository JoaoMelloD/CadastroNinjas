package dev.java10x.CadastroDeNinjas.Ninjas;

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
@Tag(name = "Ninjas", description = "Operações relacionadas ao gerenciamento de ninjas")
public class NinjaController {

    final private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Adicionar Ninja
    @Operation(
        summary = "Criar um novo ninja",
        description = "Cadastra um novo ninja no sistema com todos os seus dados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NinjaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping("/ninjas")
    public ResponseEntity<?> criar(@RequestBody NinjaDTO ninjaModel) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninjaModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO);
    }

    // Procurar Ninja Por ID
    @Operation(
        summary = "Buscar ninja por ID",
        description = "Retorna os dados de um ninja específico com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NinjaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @GetMapping("/ninjas/{id}")
    public ResponseEntity<?> listarUm(
            @Parameter(description = "ID do ninja a ser buscado", required = true, example = "1")
            @PathVariable Long id) {
        NinjaDTO ninjaDTO = ninjaService.listarNinja(id);
        if (ninjaDTO != null) {
            return ResponseEntity.ok().body(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja Não Encontrado");
    }

    // Mostrar todos os Ninjas
    @Operation(
        summary = "Listar todos os ninjas",
        description = "Retorna uma lista completa com todos os ninjas cadastrados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NinjaDTO.class)))
    })
    @GetMapping("/ninjas")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Alterar dados do Ninja
    @Operation(
        summary = "Atualizar dados de um ninja",
        description = "Atualiza as informações de um ninja existente com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NinjaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @PutMapping("/ninjas/{id}")
    public ResponseEntity<?> atualizar(
            @RequestBody NinjaDTO ninjaModel,
            @Parameter(description = "ID do ninja a ser atualizado", required = true, example = "1")
            @PathVariable Long id) {
        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(ninjaModel, id);
        if (ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja Não Encontrado");
    }

    // Deletar por ID
    @Operation(
        summary = "Deletar um ninja",
        description = "Remove permanentemente um ninja do sistema com base no seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @DeleteMapping("/ninjas/{id}")
    public ResponseEntity<?> deletar(
            @Parameter(description = "ID do ninja a ser deletado", required = true, example = "1")
            @PathVariable Long id) {
        if (ninjaService.listarNinja(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok().body("Ninja Deletado com sucesso: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja não foi encontrado");
    }
}
