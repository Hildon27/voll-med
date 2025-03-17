package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.services.MedicoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrarMedico(@Valid @RequestBody MedicoCreateDTO medicoCreateDTO) {
        MedicoResponseDTO responseDTO = medicoService.cadastrarMedico(medicoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/batch")
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

}
