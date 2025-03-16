package med.voll.api.dto.medico;

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
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private EnderecoDTO endereco;
}
