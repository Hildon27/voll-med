package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dtos.medicos.DadosAtualizacaoMedico;
import med.voll.api.dtos.medicos.DadosListagemMedico;
import med.voll.api.models.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import med.voll.api.dtos.medicos.DadosCadastroMedico;
import med.voll.api.services.MedicoService;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService sv;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        DadosListagemMedico dadosMedicoCriado = this.sv.cadastrarMedico(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dadosMedicoCriado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosMedicoCriado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable page) {
        var medicos = this.sv.listarMedicos(page);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        this.sv.atualizarMedico(dados);
        return ResponseEntity.ok(this.sv.getMedico(dados.id()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        this.sv.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok((this.sv.getMedico(id)));
    }
    
}
