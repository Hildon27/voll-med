package med.voll.api.services;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MedicoServiceInterface {
    
    public DadosListagemMedico cadastrarMedico(DadosCadastroMedico dados);
    public Page<DadosListagemMedico> listarMedicos(Pageable page);
    public void atualizarMedico(DadosAtualizacaoMedico dados);
    public void excluirMedico(Long id);
    public DadosListagemMedico getMedico(Long id);
}
