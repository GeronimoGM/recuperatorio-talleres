package dev.geronimogm.recuperatorio.dto.mapper;

import dev.geronimogm.recuperatorio.dto.request.TallerCreateRequest;
import dev.geronimogm.recuperatorio.dto.request.TallerUpdateRequest;
import dev.geronimogm.recuperatorio.dto.response.TallerResponse;
import dev.geronimogm.recuperatorio.entity.TallerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TallerMapper {

    private final VecinoMapper vecinoMapper;

    public TallerResponse entityToDto(TallerEntity entity) {
        return new TallerResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getZona(),
                entity.getTematica(),
                entity.getCupoMaximo(),
                entity.getVecinos()
                        .stream()
                        .map(vecinoMapper::entityToDto)
                        .toList()
        );
    }

    public TallerEntity createRequestToEntity(TallerCreateRequest request) {
        return new TallerEntity(
                request.nombre(),
                request.zona(),
                request.tematica(),
                request.cupoMaximo()
        );
    }

    public TallerEntity updateRequestToEntity(TallerUpdateRequest request, TallerEntity entity) {
        entity.setNombre(request.nombre());
        entity.setZona(request.zona());
        entity.setTematica(request.tematica());
        entity.setCupoMaximo(request.cupoMaximo());

        return entity;
    }

}
