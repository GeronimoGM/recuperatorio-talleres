package dev.geronimogm.recuperatorio.dto.mapper;

import dev.geronimogm.recuperatorio.dto.request.VecinoRequest;
import dev.geronimogm.recuperatorio.dto.response.VecinoResponse;
import dev.geronimogm.recuperatorio.entity.VecinoEntity;
import org.springframework.stereotype.Component;

@Component
public class VecinoMapper {

    public VecinoResponse entityToDto(VecinoEntity entity) {
        return new VecinoResponse(
                entity.getDni(),
                entity.getNombre(),
                entity.getEmail()
        );
    }

    public VecinoEntity requestToEntity(VecinoRequest request) {
        return new VecinoEntity(
                request.dni(),
                request.nombre(),
                request.email()
        );
    }

}
