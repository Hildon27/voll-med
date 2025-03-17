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
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {


    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;


    public MedicoResponseDTO cadastrarMedico(MedicoCreateDTO medicoCreateDTO) {
        Medico medico = modelMapper.map(medicoCreateDTO, Medico.class);
        medico = medicoRepository.save(medico);
        return modelMapper.map(medico, MedicoResponseDTO.class);
    }

    public List<MedicoResponseDTO> cadastrarMedicos(List<MedicoCreateDTO> medicosDTO) {
    List<Medico> medicos = medicosDTO.stream()
            .map(dto -> modelMapper.map(dto, Medico.class))
            .collect(Collectors.toList());

    List<Medico> medicosSalvos = medicoRepository.saveAll(medicos);

    return medicosSalvos.stream()
            .map(medico -> modelMapper.map(medico, MedicoResponseDTO.class))
            .collect(Collectors.toList());
    }


    public Page<MedicoResponseDTO> listarMedicos(int page) {
    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nome"));
    
    Page<Medico> medicos = medicoRepository.findAll(pageable);
    
    return medicos.map(medico -> modelMapper.map(medico, MedicoResponseDTO.class));
    }

    @Transactional
    public MedicoResponseDTO atualizarMedico(Long id, MedicoUpdateDTO medicoUpdateDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        // Atualiza apenas os campos permitidos
        if (medicoUpdateDTO.getNome() != null) {
            medico.setNome(medicoUpdateDTO.getNome());
        }

        if (medicoUpdateDTO.getTelefone() != null) {
            medico.setTelefone(medicoUpdateDTO.getTelefone());
        }

        if (medicoUpdateDTO.getEndereco() != null) {
            atualizarEndereco(medico.getEndereco(), medicoUpdateDTO.getEndereco());
        }

        medicoRepository.save(medico);

        return modelMapper.map(medico, MedicoResponseDTO.class);
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


}
