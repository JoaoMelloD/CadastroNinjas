package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Hello World!";
    }
}
