package dev.geronimogm.recuperatorio.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InscripcionRequest(

        @NotNull(message = "El DNI es obligatorio")
        @Positive(message = "El DNI debe ser mayor a 0")
        Long dni

) {

}
