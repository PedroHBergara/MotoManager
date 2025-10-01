package br.com.mottu.MottuCourtyardManager.mapper;

import br.com.mottu.MottuCourtyardManager.dto.FilialRequest;
import br.com.mottu.MottuCourtyardManager.dto.FilialResponse;
import br.com.mottu.MottuCourtyardManager.model.Filial;
import br.com.mottu.MottuCourtyardManager.model.Patio;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilialMapper {

    // Private constructor to prevent instantiation
    private FilialMapper() {}

    public static Filial toEntity(FilialRequest dto) {
        if (dto == null) {
            return null;
        }
        Filial filial = new Filial();
        filial.setNome(dto.nome());
        filial.setPais(dto.pais());
        filial.setLogradouro(dto.logradouro());
        // A lista de Patios será gerenciada pelo relacionamento e pelo PatioService/Mapper
        return filial;
    }

    public static FilialResponse toResponseDTO(Filial entity) {
        if (entity == null) {
            return null;
        }
        List<Long> patioIds = entity.getPatios() != null
                ? entity.getPatios().stream().map(Patio::getId).collect(Collectors.toList())
                : Collections.emptyList();

        return new FilialResponse(
                entity.getId(),
                entity.getNome(),
                entity.getPais(),
                entity.getLogradouro(),
                patioIds
        );
    }
}

