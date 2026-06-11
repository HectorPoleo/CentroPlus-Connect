package es.ies.puerto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ies.puerto.model.Incidencias;
import es.ies.puerto.service.IncidenciasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/incidencias")
@Tag(name = "Incidencias", description = "Registro y seguimiento de incidencias del centro")
public class IncidenciasController {

    private final IncidenciasService service;

    public IncidenciasController(IncidenciasService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las incidencias", description = "Devuelve todas las incidencias registradas, con su estado y descripción.")
    @ApiResponse(responseCode = "200", description = "Lista de incidencias obtenida correctamente")
    public List<Incidencias> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener incidencia por ID", description = "Devuelve los detalles completos de una incidencia: asunto, descripción, fecha y estado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada", content = @Content)
    })
    public ResponseEntity<Incidencias> getById(
            @Parameter(description = "ID de la incidencia", example = "1", required = true) @PathVariable int id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Registrar nueva incidencia", description = "Crea una nueva incidencia asociada a un usuario con asunto, descripción y fecha.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Incidencia registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    public ResponseEntity<Incidencias> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva incidencia", content = @Content(schema = @Schema(implementation = Incidencias.class), examples = @ExampleObject(value = """
                    {
                      "idUsuario": 1,
                      "asunto": "Vestuario sin agua caliente",
                      "descripcion": "Desde esta mañana no hay agua caliente en los vestuarios masculinos.",
                      "fecha": "2025-06-10",
                      "estado": "abierta"
                    }
                    """))) @Valid @RequestBody Incidencias incidencia) {
        Incidencias creada = service.save(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar incidencia", description = "Modifica el estado o descripción de una incidencia (p.ej. de 'abierta' a 'resuelta').")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incidencia actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<Incidencias> update(
            @Parameter(description = "ID de la incidencia a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody Incidencias incidencia) {
        return service.update(id, incidencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar incidencia", description = "Elimina definitivamente una incidencia del registro.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Incidencia eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la incidencia a eliminar", example = "1") @PathVariable int id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
