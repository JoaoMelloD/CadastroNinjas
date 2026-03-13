package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
     final private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }
    //Listar todos os ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }
    //Listar ninja unico
    public Optional<NinjaModel> listarNinja(Long id){
        return ninjaRepository.findById(id);
    }
    //Criar NInja
    public NinjaModel criarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }

}
