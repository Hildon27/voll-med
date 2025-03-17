package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.EnderecoDTO;
import med.voll.api.enumerator.Especialidade;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MedicoCreateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    private String crm;

    @NotNull
    private Especialidade especialidade;

    @NotNull
    @Valid
    private EnderecoDTO endereco;
}
