package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.paciente.PacienteCreateDTO;
import med.voll.api.dto.paciente.PacienteResponseDTO;
import med.voll.api.services.PacienteService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteResponseDTO> cadastrarMedico(@Valid @RequestBody PacienteCreateDTO pacienteCreateDTO) {
        PacienteResponseDTO responseDTO = pacienteService.cadastrarPaciente(pacienteCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
