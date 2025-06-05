package dev.geronimogm.recuperatorio.service;

import dev.geronimogm.recuperatorio.mapper.VecinoMapper;
import dev.geronimogm.recuperatorio.dto.request.VecinoRequest;
import dev.geronimogm.recuperatorio.dto.response.VecinoResponse;
import dev.geronimogm.recuperatorio.entity.VecinoEntity;
import dev.geronimogm.recuperatorio.exception.BadRequestException;
import dev.geronimogm.recuperatorio.exception.NotFoundException;
import dev.geronimogm.recuperatorio.repository.VecinoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VecinoService {

    private final VecinoRepository vecinoRepository;
    private final VecinoMapper vecinoMapper;

    @Transactional
    public VecinoResponse create(@RequestBody @Valid VecinoRequest request) {
        if (vecinoRepository.existsByDni(request.dni())) {
            throw new BadRequestException("Ya existe un vecino con ese DNI");
        }
        if (vecinoRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Ya existe un vecino con ese email");
        }

        return vecinoMapper.entityToDto(vecinoRepository.save(vecinoMapper.requestToEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<VecinoResponse> findAll() {
        return vecinoRepository.findAll()
                .stream()
                .map(vecinoMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public VecinoResponse findByDni(Long dni) {
        VecinoEntity vecino = vecinoRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Este vecino no existe"));

        return vecinoMapper.entityToDto(vecino);
    }

    @Transactional(readOnly = true)
    public List<VecinoResponse> buscar(String nombre) {
        return vecinoRepository.findByNombreLike(nombre)
                .stream()
                .map(vecinoMapper::entityToDto)
                .toList();
    }

}
