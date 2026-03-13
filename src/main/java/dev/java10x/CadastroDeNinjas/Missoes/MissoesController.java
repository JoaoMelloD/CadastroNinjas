package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/missoes")
    public String listarMissoes(){
        return "Listar todas as missões";
    }

    @GetMapping("/missoesId")
    public String listarMissao(){
        return "Listar missao 1";
    }

    @PostMapping("/missoes")
    public String criarMissao(){
        return "Missão Criada";
    }

    @PutMapping("/missoesId")
    public String atualizarMissao(){
        return "Missao 1 Atualizada";
    }

    @DeleteMapping("/missoesId")
    public String deletarMissao(){
        return "Missao 1 Deletada";
    }
}
