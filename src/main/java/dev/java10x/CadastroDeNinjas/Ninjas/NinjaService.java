package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
     final private NinjaRepository ninjaRepository;
     private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }
    //Listar todos os ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel>ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }
    //Listar ninja unico
    public NinjaDTO listarNinja(Long id){
        Optional<NinjaModel>ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }
    //Criar NInja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }
    //Deletar Ninja - Tem que ser void
    public void deletarNinja(Long id){
         ninjaRepository.deleteById(id);
    }

    //Atualizar Ninja
    public NinjaDTO atualizarNinja(NinjaDTO data, Long id){
        Optional<NinjaModel>ninjaExistente = ninjaRepository.findById(id);
       if(ninjaExistente.isPresent()){
           NinjaModel ninjaAtualizado = ninjaMapper.map(data);
           ninjaAtualizado.setId(id);
           NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
           return ninjaMapper.map(ninjaSalvo);
       }
       return null;
    }

}
