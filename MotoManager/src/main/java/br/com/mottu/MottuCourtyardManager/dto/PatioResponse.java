package br.com.mottu.MottuCourtyardManager.dto;

public record PatioResponse(
        Long id,
        int qtdMotos,
        int numPatio,
        Long filialId
) {}

