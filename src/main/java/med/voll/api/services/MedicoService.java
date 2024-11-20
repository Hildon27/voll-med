package med.voll.api.services;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.models.Medico;
import med.voll.api.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements MedicoServiceInterface{
    
    @Autowired
    private MedicoRepository rp;

    @Override
    public void cadastrarMedico(DadosCadastroMedico dados) {
        this.rp.save(new Medico(dados));
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
}
