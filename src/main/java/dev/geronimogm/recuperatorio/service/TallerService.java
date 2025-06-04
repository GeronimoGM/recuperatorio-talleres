package dev.geronimogm.recuperatorio.service;

import dev.geronimogm.recuperatorio.dto.mapper.TallerMapper;
import dev.geronimogm.recuperatorio.dto.request.InscripcionRequest;
import dev.geronimogm.recuperatorio.dto.request.TallerCreateRequest;
import dev.geronimogm.recuperatorio.dto.request.TallerUpdateRequest;
import dev.geronimogm.recuperatorio.dto.response.TallerResponse;
import dev.geronimogm.recuperatorio.entity.TallerEntity;
import dev.geronimogm.recuperatorio.entity.VecinoEntity;
import dev.geronimogm.recuperatorio.entity.enums.Zona;
import dev.geronimogm.recuperatorio.exception.BadRequestException;
import dev.geronimogm.recuperatorio.exception.NotFoundException;
import dev.geronimogm.recuperatorio.repository.TallerRepository;
import dev.geronimogm.recuperatorio.repository.VecinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;
    private final TallerMapper tallerMapper;
    private final VecinoRepository vecinoRepository;

    @Transactional(readOnly = true)
    public List<TallerResponse> getAll() {
        return tallerRepository.findAll()
                .stream()
                .map(tallerMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TallerResponse getById(Long id) {
        return tallerRepository.findById(id)
                .map(tallerMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Este taller no existe"));
    }

    @Transactional
    public TallerResponse create(TallerCreateRequest request) {
        List<VecinoEntity> vecinos = vecinoRepository.findAllById(request.dniVecinos());

        if (vecinos.size() != request.dniVecinos().size()) {
            throw new BadRequestException("Ingresaste un ID de un vecino que no existe");
        }

        TallerEntity taller = tallerMapper.createRequestToEntity(request);
        taller.getVecinos().addAll(vecinos);

        return tallerMapper.entityToDto(tallerRepository.save(taller));
    }

    @Transactional
    public TallerResponse update(Long id, TallerUpdateRequest request) {
        TallerEntity taller = tallerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Este taller no existe"));

        return tallerMapper.entityToDto(tallerRepository.save(tallerMapper.updateRequestToEntity(request, taller)));
    }

    @Transactional
    public void delete(Long id) {
        TallerEntity taller = tallerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Este taller no existe"));

        if (taller.getVecinos().size() > 3) {
            throw new BadRequestException("No se puede eliminar este taller ya que más de 3 vecinos están inscritos");
        }

        tallerRepository.delete(taller);
    }

    @Transactional(readOnly = true)
    public List<TallerResponse> filtrar(Zona zona, String nombre) {
        return tallerRepository.findByZonaAndNombreLike(zona, nombre)
                .stream()
                .map(tallerMapper::entityToDto)
                .toList();
    }

    @Transactional
    public TallerResponse inscribir(Long id, InscripcionRequest request) {
        TallerEntity taller = tallerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Este taller no existe"));

        VecinoEntity vecino = vecinoRepository.findByDni(request.dni())
                .orElseThrow(() -> new BadRequestException("Este vecino no existe"));

        taller.getVecinos().add(vecino);

        return tallerMapper.entityToDto(tallerRepository.save(taller));
    }

}
