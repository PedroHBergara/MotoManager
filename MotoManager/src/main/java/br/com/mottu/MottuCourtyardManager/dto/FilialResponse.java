package br.com.mottu.MottuCourtyardManager.dto;

import java.util.List;

public record FilialResponse(
        Long id,
        String nome,
        String pais,
        String logradouro,
        List<Long> patioIds
) {}

