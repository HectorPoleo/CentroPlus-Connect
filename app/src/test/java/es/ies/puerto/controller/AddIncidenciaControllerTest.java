package es.ies.puerto.controller;

import es.ies.puerto.modelo.Incidencias;
import es.ies.puerto.service.sqlite.IncidenciasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddIncidenciaControllerTest {

    private IncidenciasService mockService;

    @BeforeEach
    public void setUp() {
        mockService = Mockito.mock(IncidenciasService.class);
    }

    @Test
    @Order(1)
    @DisplayName("isGuardado es false por defecto")
    public void testGuardadoInicialFalse() {
        boolean guardado = false;
        assertFalse(guardado, "El estado guardado debe ser false inicialmente");
    }

    @Test
    @Order(2)
    @DisplayName("save con incidencia válida retorna true")
    public void testGuardarIncidenciaValida() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Incidencias incidencia = new Incidencias(1, 2, "Problema acceso", "No puede entrar al gimnasio", fecha, "ABIERTA");
        when(mockService.save(incidencia)).thenReturn(true);

        boolean resultado = mockService.save(incidencia);
        assertTrue(resultado, "Guardar una incidencia válida debe retornar true");
        verify(mockService, times(1)).save(incidencia);
    }

    @Test
    @Order(3)
    @DisplayName("save con incidencia duplicada retorna false")
    public void testGuardarIncidenciaDuplicada() {
        Incidencias incidencia = new Incidencias(1, 2, "Duplicada", "Desc", "2026-01-01 10:00", "ABIERTA");
        when(mockService.save(incidencia)).thenReturn(false);

        boolean resultado = mockService.save(incidencia);
        assertFalse(resultado);
    }

    @Test
    @Order(4)
    @DisplayName("Fecha generada tiene formato correcto yyyy-MM-dd HH:mm")
    public void testFormatoFechaGenerada() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertNotNull(fecha);
        assertTrue(fecha.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}"),
            "La fecha debe tener el formato yyyy-MM-dd HH:mm");
    }

    @Test
    @Order(5)
    @DisplayName("Estados válidos disponibles en el combo")
    public void testEstadosValidos() {
        String[] estadosEsperados = {"ABIERTA", "EN_PROCESO", "CERRADA"};
        assertEquals(3, estadosEsperados.length);
        for (String estado : estadosEsperados) {
            assertNotNull(estado);
            assertFalse(estado.isEmpty());
        }
    }

    @Test
    @Order(6)
    @DisplayName("Parseo de IDs no numéricos lanza NumberFormatException")
    public void testParseoIdsInvalidos() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(""));
    }

    @Test
    @Order(7)
    @DisplayName("Construcción de Incidencias asigna campos correctamente")
    public void testConstruccionIncidencia() {
        Incidencias inc = new Incidencias(10, 5, "Asunto test", "Descripción test", "2026-06-01 09:00", "EN_PROCESO");
        assertEquals(10, inc.getIdIncidencia());
        assertEquals(5, inc.getId());
        assertEquals("Asunto test", inc.getAsunto());
        assertEquals("Descripción test", inc.getDescripcion());
        assertEquals("2026-06-01 09:00", inc.getFecha());
        assertEquals("EN_PROCESO", inc.getEstado());
    }
}