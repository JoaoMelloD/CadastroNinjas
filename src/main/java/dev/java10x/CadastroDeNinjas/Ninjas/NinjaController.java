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
    public NinjaDTO criar(@RequestBody NinjaDTO ninjaModel){
        return ninjaService.criarNinja(ninjaModel);
    }

    //Procurar Ninja Por ID
    @GetMapping("/ninjas/{id}")
    public NinjaDTO listarUm(@PathVariable Long id){
        return ninjaService.listarNinja(id);
    }
    //Mostrar todos os Ninjas
    @GetMapping("/ninjas")
    public List<NinjaDTO> listarNinjas(){
        return ninjaService.listarNinjas();
    }
    //Alterar dados do Ninja
    @PutMapping("/ninjas/{id}")
    public NinjaDTO atualizar(@RequestBody NinjaDTO ninjaModel, @PathVariable Long id){
        return ninjaService.atualizarNinja(ninjaModel, id);
    }
    //Deletar por ID
    @DeleteMapping("/ninjas/{id}")
    public void deletar(@PathVariable Long id){
         ninjaService.deletarNinja(id);
    }

}
