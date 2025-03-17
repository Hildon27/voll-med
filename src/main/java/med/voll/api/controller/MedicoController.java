package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.dto.medico.MedicoUpdateDTO;
import med.voll.api.services.MedicoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoResponseDTO> cadastrarMedico(@Valid @RequestBody MedicoCreateDTO medicoCreateDTO) {
        MedicoResponseDTO responseDTO = medicoService.cadastrarMedico(medicoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/batch")
    @Transactional
    public ResponseEntity<List<MedicoResponseDTO>> cadastrarMedicos(
            @Valid @RequestBody List<MedicoCreateDTO> medicosDTO) {

        List<MedicoResponseDTO> medicosCadastrados = medicoService.cadastrarMedicos(medicosDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(medicosCadastrados);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> listarMedicos(
            @RequestParam(defaultValue = "0") int page) {
        Page<MedicoResponseDTO> medicos = medicoService.listarMedicos(page);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoResponseDTO> atualizarMedico(
            @PathVariable Long id,
            @Valid @RequestBody MedicoUpdateDTO medicoUpdateDTO) {

        MedicoResponseDTO medicoAtualizado = medicoService.atualizarMedico(id, medicoUpdateDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }

}
