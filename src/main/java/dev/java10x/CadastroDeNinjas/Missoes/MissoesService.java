package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    final private MissoesRepository missoesRepository;
    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    //Criar Missão
    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }
    //Listar Missoes
    public List<MissoesModel> listarMissoes(){return missoesRepository.findAll();}
    //Listar Missao
    public Optional<MissoesModel> listarMissao(Long id){
        return missoesRepository.findById(id);
    }
    //Atualizar Missao
    public MissoesModel atualizar(MissoesModel data, Long id){
        if(missoesRepository.existsById(id)){
            data.setId(id);
            return missoesRepository.save(data);
        }
        return null;
    }
    //Deletar Missao
    public void deletar(Long id){
        missoesRepository.deleteById(id);
    }




}
