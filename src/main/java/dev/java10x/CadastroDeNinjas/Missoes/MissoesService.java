package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    final private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Criar Missão
    public MissoesDTO criarMissao(MissoesDTO missaoDto){
        MissoesModel missao = missoesMapper.map(missaoDto);
        missao = missoesRepository.save(missao);
        return  missoesMapper.map(missao);
    }
    //Listar Missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel>missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }
    //Listar Missao
    public MissoesDTO listarMissao(Long id){
        Optional<MissoesModel>missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }
    //Atualizar Missao
    public MissoesDTO atualizar(MissoesDTO data, Long id){
        Optional<MissoesModel>missaoExistente = missoesRepository.findById(id);
        if(missaoExistente.isPresent()){
            MissoesModel missaoAtualizada = missoesMapper.map(data);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
    //Deletar Missao
    public void deletar(Long id){
        missoesRepository.deleteById(id);
    }




}
