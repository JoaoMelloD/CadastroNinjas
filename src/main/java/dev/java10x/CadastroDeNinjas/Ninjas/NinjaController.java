package dev.java10x.CadastroDeNinjas.Ninjas;

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
    public NinjaModel criar(@RequestBody NinjaModel ninjaModel){
        return ninjaService.criarNinja(ninjaModel);
    }

    //Procurar Ninja Por ID
    @GetMapping("/ninjas/{id}")
    public Optional<NinjaModel> listarUm(@PathVariable Long id){
        return ninjaService.listarNinja(id);
    }
    //Mostrar todos os Ninjas
    @GetMapping("/ninjas")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }
    //Alterar dados do Ninja
    @PutMapping("/ninjas/{id}")
    public void atualizar(@RequestBody NinjaModel ninjaModel, @PathVariable Long id){
    }
    //Deletar por ID
    @DeleteMapping("/ninjasID")
    public String deletar(){
        return "Ninja 1 Deletado!";
    }

}
