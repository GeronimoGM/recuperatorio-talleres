package dev.geronimogm.recuperatorio.controller;

import dev.geronimogm.recuperatorio.dto.request.VecinoRequest;
import dev.geronimogm.recuperatorio.dto.response.VecinoResponse;
import dev.geronimogm.recuperatorio.service.VecinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vecinos")
@RequiredArgsConstructor
public class VecinoController {

    private final VecinoService vecinoService;

    @PostMapping
    public ResponseEntity<VecinoResponse> create(@RequestBody @Valid VecinoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vecinoService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VecinoResponse>> findAll() {
        return ResponseEntity.ok(vecinoService.findAll());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<VecinoResponse> findByDni(@PathVariable Long dni) {
        return ResponseEntity.ok(vecinoService.findByDni(dni));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<VecinoResponse>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(vecinoService.buscar(nombre));
    }
}
