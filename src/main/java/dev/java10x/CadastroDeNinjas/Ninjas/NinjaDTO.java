package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de um ninja")
public class NinjaDTO {

    @Schema(description = "ID único do ninja", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do ninja", example = "Naruto Uzumaki")
    private String name;

    @Schema(description = "E-mail do ninja", example = "naruto@konoha.com")
    private String email;

    @Schema(description = "Idade do ninja", example = "17")
    private int idade;

    @Schema(description = "Rank do ninja (ex: Genin, Chunin, Jonin)", example = "Hokage")
    private String rank;

    @Schema(description = "URL da imagem do ninja", example = "https://example.com/naruto.png")
    private String imgUrl;

    @Schema(description = "Missão atribuída ao ninja")
    private MissoesModel missoes;
}
