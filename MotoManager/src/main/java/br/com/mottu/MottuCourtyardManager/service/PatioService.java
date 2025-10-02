package br.com.mottu.MottuCourtyardManager.service; // Changed package

import br.com.mottu.MottuCourtyardManager.dto.PatioRequest;
import br.com.mottu.MottuCourtyardManager.dto.PatioResponse;
import br.com.mottu.MottuCourtyardManager.mapper.PatioMapper;
import br.com.mottu.MottuCourtyardManager.model.Filial;
import br.com.mottu.MottuCourtyardManager.model.Patio;
import br.com.mottu.MottuCourtyardManager.repository.FilialRepository;
import br.com.mottu.MottuCourtyardManager.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatioService {

    private final PatioRepository patioRepository;
    private final FilialRepository filialRepository;

    @Autowired
    public PatioService(PatioRepository patioRepository, FilialRepository filialRepository) { // Renamed constructor
        this.patioRepository = patioRepository;
        this.filialRepository = filialRepository;
    }

    // Removed @Override
    @Transactional(readOnly = true)
    public Page<PatioResponse> findAll(Pageable pageable) {
        Page<Patio> patios = patioRepository.findAll(pageable);
        return patios.map(PatioMapper::toResponse);
    }

    // Removed @Override
    @Transactional(readOnly = true)
    public Page<PatioResponse> findByFilialId(Long filialId, Pageable pageable) {
        // First check if Filial exists
        if (!filialRepository.existsById(filialId)) {
            throw new EntityNotFoundException("Filial não encontrada com ID: " + filialId);
        }
        Page<Patio> patios = patioRepository.findByFilialId(filialId, pageable);
        return patios.map(PatioMapper::toResponse);
    }

    // Removed @Override
    @Transactional(readOnly = true)
    public PatioResponse findById(Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + id));
        return PatioMapper.toResponse(patio);
    }

    // Removed @Override
    @Transactional
    public PatioResponse create(PatioRequest patioRequestDTO) {
        Filial filial = filialRepository.findById(patioRequestDTO.filialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada com ID: " + patioRequestDTO.filialId() + " para associar ao Pátio"));

        Patio patio = PatioMapper.toEntity(patioRequestDTO, filial);
        Patio savedPatio = patioRepository.save(patio);
        return PatioMapper.toResponse(savedPatio);
    }

    // Removed @Override
    @Transactional
    public PatioResponse update(Long id, PatioRequest patioRequestDTO) {
        Patio existingPatio = patioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + id));

        // Check if the Filial needs to be updated
        if (!existingPatio.getFilial().getId().equals(patioRequestDTO.filialId())) {
            Filial newFilial = filialRepository.findById(patioRequestDTO.filialId())
                    .orElseThrow(() -> new EntityNotFoundException("Nova Filial não encontrada com ID: " + patioRequestDTO.filialId()));
            existingPatio.setFilial(newFilial);
        }

        // Update other fields
        existingPatio.setQtdMotos(patioRequestDTO.qtdMotos());
        existingPatio.setNumPatio(patioRequestDTO.numPatio());

        Patio updatedPatio = patioRepository.save(existingPatio);
        return PatioMapper.toResponse(updatedPatio);
    }

    // Removed @Override
    @Transactional
    public void delete(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new EntityNotFoundException("Pátio não encontrado com ID: " + id);
        }
        patioRepository.deleteById(id);
    }

}

