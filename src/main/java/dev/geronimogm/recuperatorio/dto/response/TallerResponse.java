package dev.geronimogm.recuperatorio.dto.response;

import dev.geronimogm.recuperatorio.entity.enums.Zona;

import java.util.List;

public record TallerResponse(
        Long id,
        String nombre,
        Zona zona,
        String tematica,
        int cupoMaximo,
        List<VecinoResponse> vecinos
) {

}
