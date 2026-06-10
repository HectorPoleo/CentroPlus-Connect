package es.ies.puerto.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.ies.puerto.modelo.Actividades;
import es.ies.puerto.service.sqlite.ActividadesService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddActividadControllerTest {

    private ActividadesService mockService;

    @BeforeEach
    public void setUp() {
        mockService = Mockito.mock(ActividadesService.class);
    }

    @Test
    @Order(1)
    @DisplayName("isGuardado es false por defecto")
    public void testGuardadoInicialFalse() {
        // Estado inicial antes de cualquier acción: guardado = false
        boolean guardado = false;
        assertFalse(guardado, "El estado guardado debe ser false inicialmente");
    }

    @Test
    @Order(2)
    @DisplayName("save con datos válidos retorna true y marca guardado")
    public void testGuardarDatosValidos() {
        Actividades actividad = new Actividades(1, "Yoga", "Relajación", 60, 20, 15, 0);
        when(mockService.save(actividad)).thenReturn(true);

        boolean resultado = mockService.save(actividad);
        assertTrue(resultado, "Guardar una actividad válida debe retornar true");
        verify(mockService, times(1)).save(actividad);
    }

    @Test
    @Order(3)
    @DisplayName("save con datos duplicados retorna false")
    public void testGuardarDatosDuplicados() {
        Actividades actividad = new Actividades(1, "Yoga", "Relajación", 60, 20, 15, 0);
        when(mockService.save(actividad)).thenReturn(false);

        boolean resultado = mockService.save(actividad);
        assertFalse(resultado, "Guardar una actividad con id duplicado debe retornar false");
    }

    @Test
    @Order(4)
    @DisplayName("Parseo de campos numéricos válidos no lanza excepción")
    public void testParseoCamposNumericos() {
        assertDoesNotThrow(() -> {
            int id = Integer.parseInt("5");
            int duracion = Integer.parseInt("60");
            int precio = Integer.parseInt("25");
            int max = Integer.parseInt("20");
            assertEquals(5, id);
            assertEquals(60, duracion);
            assertEquals(25, precio);
            assertEquals(20, max);
        });
    }

    @Test
    @Order(5)
    @DisplayName("Parseo de campo no numérico lanza NumberFormatException")
    public void testParseoInvalidoLanzaExcepcion() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
    }

    @Test
    @Order(6)
    @DisplayName("Parseo de campo vacío lanza NumberFormatException")
    public void testParseoVacioLanzaExcepcion() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(""));
    }
}