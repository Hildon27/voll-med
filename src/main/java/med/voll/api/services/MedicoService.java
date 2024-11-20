package med.voll.api.services;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.models.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService implements MedicoServiceInterface{
    
    @Autowired
    private MedicoRepository rp;

    @Override
    public DadosListagemMedico cadastrarMedico(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados);
        this.rp.save(medico);
        return new DadosListagemMedico(medico);
    }

    @Override
    public Page<DadosListagemMedico> listarMedicos(Pageable page) {
        return this.rp.findAllByAtivoTrue(page).map(DadosListagemMedico::new);
    }

    @Override
    public void atualizarMedico(DadosAtualizacaoMedico dados) {
        Medico medico  = this.rp.getReferenceById(dados.id());
        medico.atualizarDados(dados);
    }

    @Override
    public void excluirMedico(Long id) {
        Medico medico  = this.rp.getReferenceById(id);
        medico.setAtivo(false);
    }

    @Override
    public DadosListagemMedico getMedico(Long id) {
        Medico medico = this.rp.getReferenceById(id);
        return new DadosListagemMedico(medico);
    }
}
