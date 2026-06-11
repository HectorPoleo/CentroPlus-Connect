package es.ies.puerto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.ies.puerto.model.Actividades;
import es.ies.puerto.service.ActividadesService;
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
@RequestMapping("/api/actividades")
@Tag(name = "Actividades", description = "Catálogo de actividades del centro y control de plazas")
public class ActividadesController {

    private final ActividadesService service;

    public ActividadesController(ActividadesService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las actividades", description = "Devuelve el catálogo completo de actividades disponibles con sus plazas.")
    @ApiResponse(responseCode = "200", description = "Catálogo de actividades obtenido correctamente")
    public List<Actividades> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener actividad por ID", description = "Devuelve los detalles de una actividad específica, incluyendo plazas disponibles.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actividad encontrada"),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada", content = @Content)
    })
    public ResponseEntity<Actividades> getById(
            @Parameter(description = "ID de la actividad", example = "1", required = true) @PathVariable int id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nueva actividad", description = "Añade una nueva actividad al catálogo del centro deportivo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Actividad creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    public ResponseEntity<Actividades> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva actividad", content = @Content(schema = @Schema(implementation = Actividades.class), examples = @ExampleObject(value = """
                    {
                      "nombre": "Yoga matutino",
                      "tipoActividad": "yoga",
                      "duracion": 60,
                      "precio": 12.50,
                      "plazasMaximas": 15,
                      "plazasOcupadas": 0
                    }
                    """))) @Valid @RequestBody Actividades actividad) {
        Actividades creada = service.save(actividad);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar actividad", description = "Modifica los datos de una actividad existente, como precio, plazas o duración.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actividad actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<Actividades> update(
            @Parameter(description = "ID de la actividad a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody Actividades actividad) {
        return service.update(id, actividad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar actividad", description = "Elimina una actividad del catálogo. Ten en cuenta que puede haber reservas asociadas.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Actividad eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la actividad a eliminar", example = "1") @PathVariable int id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
