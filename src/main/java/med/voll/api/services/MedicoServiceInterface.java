package med.voll.api.services;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MedicoServiceInterface {
    
    public void cadastrarMedico(DadosCadastroMedico dados);
    public Page<DadosListagemMedico> listarMedicos(Pageable page);
    public void atualizarMedico(DadosAtualizacaoMedico dados);
    public void excluirMedico(Long id);
}
