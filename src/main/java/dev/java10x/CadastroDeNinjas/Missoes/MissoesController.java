package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MissoesController {

    final private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {this.missoesService = missoesService;}

    @GetMapping("/missoes")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/missoes/{id}")
    public Optional<MissoesModel> listarMissao(@PathVariable Long id){
        return missoesService.listarMissao(id);
    }

    @PostMapping("/missoes")
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){
        return missoesService.criarMissao(missao);
    }

    @PutMapping("/missoes/{id}")
    public MissoesModel atualizarMissao(@RequestBody MissoesModel missoesModel, @PathVariable Long id){
        return missoesService.atualizar(missoesModel,id);
    }

    @DeleteMapping("/missoes/{id}")
    public void deletarMissao(@PathVariable Long id){
             missoesService.deletar(id);
    }
}
