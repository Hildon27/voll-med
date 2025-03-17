package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDTO {

    @NotBlank
    private String logradouro;

    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
}
