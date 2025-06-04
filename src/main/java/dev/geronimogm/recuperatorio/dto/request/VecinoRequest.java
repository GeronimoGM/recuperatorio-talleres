package dev.geronimogm.recuperatorio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VecinoRequest(

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    String email,

    @NotNull(message = "El DNI es obligatorio")
    @Positive(message = "El DNI debe ser mayor a 0")
    Long dni

) {

}
