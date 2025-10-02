package br.com.mottu.MottuCourtyardManager.mapper;

import br.com.mottu.MottuCourtyardManager.dto.PatioRequest;
import br.com.mottu.MottuCourtyardManager.dto.PatioResponse;
import br.com.mottu.MottuCourtyardManager.model.Filial;
import br.com.mottu.MottuCourtyardManager.model.Patio;

public class PatioMapper {

    private PatioMapper() {}

    public static Patio toEntity(PatioRequest dto, Filial filial) {
        if (dto == null || filial == null) {
            return null;
        }
        Patio patio = new Patio();
        patio.setQtdMotos(dto.qtdMotos());
        patio.setNumPatio(dto.numPatio());
        patio.setFilial(filial); // Set the fetched Filial entity
        return patio;
    }

    public static PatioResponse toResponse(Patio entity) {
        if (entity == null) {
            return null;
        }
        // Ensure filial is not null before accessing its ID
        Long filialId = (entity.getFilial() != null) ? entity.getFilial().getId() : null;

        return new PatioResponse(
                entity.getId(),
                entity.getQtdMotos(),
                entity.getNumPatio(),
                filialId
        );
    }}

