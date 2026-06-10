package es.ies.puerto.controller;

import es.ies.puerto.model.Reservas;
import es.ies.puerto.service.ReservasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Reservas de actividades por parte de los usuarios")
public class ReservasController {

    private final ReservasService service;

    public ReservasController(ReservasService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
        summary = "Listar todas las reservas",
        description = "Devuelve todas las reservas registradas en el sistema con su estado actual."
    )
    @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida correctamente")
    public List<Reservas> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener reserva por ID",
        description = "Devuelve los detalles de una reserva: usuario, actividad, fecha y estado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content)
    })
    public ResponseEntity<Reservas> getById(
            @Parameter(description = "ID de la reserva", example = "1", required = true)
            @PathVariable int id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Crear nueva reserva",
        description = "Registra una reserva de un usuario para una actividad concreta."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    public ResponseEntity<Reservas> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos de la nueva reserva",
                content = @Content(
                    schema = @Schema(implementation = Reservas.class),
                    examples = @ExampleObject(value = """
                        {
                          "idUsuario": 1,
                          "idActividad": 2,
                          "fecha": "2025-06-15",
                          "estado": "confirmada"
                        }
                        """)
                )
            )
            @Valid @RequestBody Reservas reserva) {
        Reservas creada = service.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar reserva",
        description = "Modifica el estado u otros datos de una reserva existente (p.ej. de 'confirmada' a 'cancelada')."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reserva actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<Reservas> update(
            @Parameter(description = "ID de la reserva a actualizar", example = "1")
            @PathVariable int id,
            @Valid @RequestBody Reservas reserva) {
        return service.update(id, reserva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar reserva",
        description = "Elimina definitivamente una reserva del sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Reserva eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la reserva a eliminar", example = "1")
            @PathVariable int id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
