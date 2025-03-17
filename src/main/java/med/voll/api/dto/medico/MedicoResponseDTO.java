package med.voll.api.dto.medico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.enumerator.Especialidade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private boolean ativo;
    private Especialidade especialidade;
}
