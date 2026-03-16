package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NinjaController {

    final private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    //Adicionar Ninja
    @PostMapping("/ninjas")
    public ResponseEntity<?> criar(@RequestBody NinjaDTO ninjaModel)
    {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninjaModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO);
    }

    //Procurar Ninja Por ID
    @GetMapping("/ninjas/{id}")
    public ResponseEntity<?> listarUm(@PathVariable Long id){
        NinjaDTO ninjaDTO = ninjaService.listarNinja(id);
        if(ninjaDTO != null){
            return ResponseEntity.ok()
                    .body(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja Não Encontrado");
    }
    //Mostrar todos os Ninjas
    @GetMapping("/ninjas")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }
    //Alterar dados do Ninja
    @PutMapping("/ninjas/{id}")
    public ResponseEntity<?> atualizar(@RequestBody NinjaDTO ninjaModel, @PathVariable Long id){
        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(ninjaModel, id);
        if(ninjaDTO != null){
            return ResponseEntity.ok(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja Não Encontrado");
    }
    //Deletar por ID
    @DeleteMapping("/ninjas/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
             if(ninjaService.listarNinja(id) != null){
                 ninjaService.deletarNinja(id);
                 return ResponseEntity.ok()
                         .body("Ninja Deletado com sucesso: " + id);
             }
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body("Ninja não foi encontrado");

    }

}
