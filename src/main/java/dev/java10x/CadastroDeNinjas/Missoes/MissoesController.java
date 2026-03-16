package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MissoesController {

    final private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {this.missoesService = missoesService;}

    @GetMapping("/missoes")
    public List<MissoesDTO> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/missoes/{id}")
    public MissoesDTO listarMissao(@PathVariable Long id){
        return missoesService.listarMissao(id);
    }

    @PostMapping("/missoes")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missao){
        return missoesService.criarMissao(missao);
    }

    @PutMapping("/missoes/{id}")
    public MissoesDTO atualizarMissao(@RequestBody MissoesDTO missoesModel, @PathVariable Long id){
        return missoesService.atualizar(missoesModel,id);
    }

    @DeleteMapping("/missoes/{id}")
    public void deletarMissao(@PathVariable Long id){
             missoesService.deletar(id);
    }
}
