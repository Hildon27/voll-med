package med.voll.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.dto.paciente.PacienteCreateDTO;
import med.voll.api.dto.paciente.PacienteResponseDTO;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PacienteResponseDTO cadastrarPaciente(PacienteCreateDTO pacienteCreateDTO) {
        Paciente paciente = modelMapper.map(pacienteCreateDTO, Paciente.class);
        paciente = pacienteRepository.save(paciente);
        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    public List<PacienteResponseDTO> cadastrarPacientes(List<PacienteCreateDTO> pacienteCreateDTO) {
        List<Paciente> medicos = pacienteCreateDTO.stream()
                .map(dto -> modelMapper.map(dto, Paciente.class))
                .collect(Collectors.toList());

        List<Paciente> pacientesSalvos = pacienteRepository.saveAll(medicos);

        return pacientesSalvos.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Page<PacienteResponseDTO> listarPacientes(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Paciente> pacientes = pacienteRepository.findByAtivoTrue(pageable);

        return pacientes.map(paciente -> modelMapper.map(paciente, PacienteResponseDTO.class));
    }

}
