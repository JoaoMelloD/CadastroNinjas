package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.DificuldadeMissao;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de uma missão")
public class MissoesDTO {

    @Schema(description = "ID único da missão", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da missão", example = "Recuperar o Pergaminho Proibido")
    private String nome;

    @Schema(description = "Nível de dificuldade da missão", example = "RANK_A")
    private DificuldadeMissao dificuldade;

    @Schema(description = "Lista de ninjas atribuídos à missão")
    private List<NinjaModel> ninjas;
}
