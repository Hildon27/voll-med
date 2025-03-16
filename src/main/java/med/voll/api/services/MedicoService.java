package med.voll.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.dto.medico.MedicoCreateDTO;
import med.voll.api.dto.medico.MedicoResponseDTO;
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

}
