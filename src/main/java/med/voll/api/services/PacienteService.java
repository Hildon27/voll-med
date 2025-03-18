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

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import med.voll.api.dto.EnderecoDTO;
import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
import med.voll.api.dto.medico.MedicoUpdateDTO;
import med.voll.api.dto.paciente.PacienteCreateDTO;
import med.voll.api.dto.paciente.PacienteResponseDTO;
import med.voll.api.dto.paciente.PacienteUpdateDTO;
import med.voll.api.model.Endereco;
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

    public PacienteCreateDTO listarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        PacienteCreateDTO pacienteCreateDTO = modelMapper.map(paciente, PacienteCreateDTO.class);
        return pacienteCreateDTO;
    }

    @Transactional
    public PacienteResponseDTO atualizarPaciente(Long id, PacienteUpdateDTO pacienteUpdateDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        if (pacienteUpdateDTO.getNome() != null) {
            paciente.setNome(pacienteUpdateDTO.getNome());
        }

        if (pacienteUpdateDTO.getTelefone() != null) {
            paciente.setTelefone(pacienteUpdateDTO.getTelefone());
        }

        if (pacienteUpdateDTO.getEndereco() != null) {
            atualizarEndereco(paciente.getEndereco(), pacienteUpdateDTO.getEndereco());
        }

        pacienteRepository.save(paciente);

        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    private void atualizarEndereco(Endereco endereco, EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getLogradouro() != null) {
            endereco.setLogradouro(enderecoDTO.getLogradouro());
        }
        if (enderecoDTO.getNumero() != null) {
            endereco.setNumero(enderecoDTO.getNumero());
        }
        if (enderecoDTO.getComplemento() != null) {
            endereco.setComplemento(enderecoDTO.getComplemento());
        }
        if (enderecoDTO.getBairro() != null) {
            endereco.setBairro(enderecoDTO.getBairro());
        }
        if (enderecoDTO.getCidade() != null) {
            endereco.setCidade(enderecoDTO.getCidade());
        }
        if (enderecoDTO.getUf() != null) {
            endereco.setUf(enderecoDTO.getUf());
        }
        if (enderecoDTO.getCep() != null) {
            endereco.setCep(enderecoDTO.getCep());
        }
    }

    @Transactional
    public void excluirPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }

}
