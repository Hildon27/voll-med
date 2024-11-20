package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.services.MedicoService;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService sv;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
          this.sv.cadastrarMedico(dados);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable page) {
        return this.sv.listarMedicos(page);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        this.sv.atualizarMedico(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        this.sv.excluirMedico(id);
    }
    
}
