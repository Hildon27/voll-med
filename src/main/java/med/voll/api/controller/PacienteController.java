package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.dto.medico.MedicoUpdateDTO;
import med.voll.api.dto.paciente.PacienteCreateDTO;
import med.voll.api.dto.paciente.PacienteResponseDTO;
import med.voll.api.dto.paciente.PacienteUpdateDTO;
import med.voll.api.services.PacienteService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(
            @Valid @RequestBody PacienteCreateDTO pacienteCreateDTO) {
        PacienteResponseDTO responseDTO = pacienteService.cadastrarPaciente(pacienteCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/batch")
    @Transactional
    public ResponseEntity<List<PacienteResponseDTO>> cadastrarPacientes(
            @Valid @RequestBody List<PacienteCreateDTO> pacienteCreateDTOs) {

        List<PacienteResponseDTO> pacientesCadastrados = pacienteService.cadastrarPacientes(pacienteCreateDTOs);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacientesCadastrados);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponseDTO>> listarPacientes(
            @RequestParam(defaultValue = "0") int page) {
        Page<PacienteResponseDTO> pacientes = pacienteService.listarPacientes(page);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(
            @PathVariable Long id,
            @Valid @RequestBody PacienteUpdateDTO pacienteUpdateDTO) {

        PacienteResponseDTO pacienteAtualizado = pacienteService.atualizarPaciente(id, pacienteUpdateDTO);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
