package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.services.MedicoService;
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

}
