package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.DificuldadeMissao;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missao")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private DificuldadeMissao dificuldade;

    // Uma missão pode ter uma N ninjas
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;

}
