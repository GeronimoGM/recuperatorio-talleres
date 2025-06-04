package dev.geronimogm.recuperatorio.controller;

import dev.geronimogm.recuperatorio.dto.request.InscripcionRequest;
import dev.geronimogm.recuperatorio.dto.request.TallerCreateRequest;
import dev.geronimogm.recuperatorio.dto.request.TallerUpdateRequest;
import dev.geronimogm.recuperatorio.dto.response.TallerResponse;
import dev.geronimogm.recuperatorio.entity.enums.Zona;
import dev.geronimogm.recuperatorio.service.TallerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
public class TallerController {

    private final TallerService tallerService;

    @GetMapping
    public ResponseEntity<List<TallerResponse>> getAll() {
        return ResponseEntity.ok(tallerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tallerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TallerResponse> create(
            @RequestBody @Valid TallerCreateRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tallerService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TallerResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid TallerUpdateRequest request
    ) {
        return ResponseEntity.ok(tallerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tallerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<TallerResponse>> filtrar(
            @RequestParam Zona zona,
            @RequestParam String nombre
    ) {
        return ResponseEntity.ok(tallerService.filtrar(zona, nombre));
    }

    @PostMapping("/{id}/inscribir")
    public ResponseEntity<TallerResponse> inscribir(
            @PathVariable Long id,
            @RequestBody @Valid InscripcionRequest request
    ) {
        return ResponseEntity.ok(tallerService.inscribir(id, request));
    }

}
