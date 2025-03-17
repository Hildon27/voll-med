package med.voll.api.dto.paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.EnderecoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteUpdateDTO {
    
    private String nome;
    private String telefone;
    private EnderecoDTO endereco;
}
