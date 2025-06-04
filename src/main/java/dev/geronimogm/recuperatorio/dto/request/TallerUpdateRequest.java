package dev.geronimogm.recuperatorio.dto.request;

import dev.geronimogm.recuperatorio.entity.enums.Zona;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TallerUpdateRequest(

    @NotBlank(message = "El nombre del taller es obligatorio")
    String nombre,

    @NotNull(message = "La zona es obligatoria (Norte, Sur, Este, Oeste)")
    Zona zona,

    @NotBlank(message = "La temática es obligatoria")
    String tematica,

    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "El cupo máximo debe ser al menos 1")
    Integer cupoMaximo

) {

}
