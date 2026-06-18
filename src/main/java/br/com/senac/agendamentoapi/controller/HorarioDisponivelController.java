package br.com.senac.agendamentoapi.controller;

import br.com.senac.agendamentoapi.dto.HorarioDisponivelRequest;
import br.com.senac.agendamentoapi.dto.HorarioDisponivelResponse;
import br.com.senac.agendamentoapi.service.HorarioDisponivelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/horarios-disponiveis")
@Tag(name = "Horários disponíveis")
@CrossOrigin("*")    
public class HorarioDisponivelController {

    private final HorarioDisponivelService service;

    public HorarioDisponivelController(HorarioDisponivelService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista registros não apagados logicamente")
    public ResponseEntity<List<HorarioDisponivelResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um registro por ID")
    public ResponseEntity<HorarioDisponivelResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo registro")
    public ResponseEntity<HorarioDisponivelResponse> criar(@RequestBody @Valid HorarioDisponivelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um registro existente")
    public ResponseEntity<HorarioDisponivelResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid HorarioDisponivelRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente um registro, alterando status para -1")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
