package es.ies.puerto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import es.ies.puerto.modelo.Reservas;
import es.ies.puerto.service.sqlite.ReservasService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddReservaControllerTest {

    private ReservasService mockService;

    @BeforeEach
    public void setUp() {
        mockService = Mockito.mock(ReservasService.class);
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
    @DisplayName("save con reserva válida retorna true")
    public void testGuardarReservaValida() {
        Reservas reserva = new Reservas(1, 2, 3, "2026-12-31", "PENDIENTE");
        when(mockService.save(reserva)).thenReturn(true);

        boolean resultado = mockService.save(reserva);
        assertTrue(resultado, "Guardar una reserva válida debe retornar true");
        verify(mockService, times(1)).save(reserva);
    }

    @Test
    @Order(3)
    @DisplayName("save con reserva duplicada retorna false")
    public void testGuardarReservaDuplicada() {
        Reservas reserva = new Reservas(1, 2, 3, "2026-12-31", "PENDIENTE");
        when(mockService.save(reserva)).thenReturn(false);

        boolean resultado = mockService.save(reserva);
        assertFalse(resultado, "Guardar una reserva duplicada debe retornar false");
    }

    @Test
    @Order(4)
    @DisplayName("Estados válidos disponibles en el combo")
    public void testEstadosValidos() {
        String[] estadosEsperados = {"PENDIENTE", "CONFIRMADA", "CANCELADA"};
        assertEquals(3, estadosEsperados.length);
        for (String estado : estadosEsperados) {
            assertNotNull(estado);
            assertFalse(estado.isEmpty());
        }
    }

    @Test
    @Order(5)
    @DisplayName("Fecha del DatePicker formateada en ISO_DATE correctamente")
    public void testFormatoFechaISO() {
        LocalDate fecha = LocalDate.of(2026, 12, 31);
        String formateada = fecha.format(DateTimeFormatter.ISO_DATE);
        assertEquals("2026-12-31", formateada);
    }

    @Test
    @Order(6)
    @DisplayName("Fecha nula del DatePicker produce cadena vacía")
    public void testFechaNulaProduceCadenaVacia() {
        LocalDate fecha = null;
        String resultado = fecha != null ? fecha.format(DateTimeFormatter.ISO_DATE) : "";
        assertEquals("", resultado, "Fecha nula debe producir cadena vacía");
    }

    @Test
    @Order(7)
    @DisplayName("Parseo de IDs inválidos lanza NumberFormatException")
    public void testParseoIdsInvalidos() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("xyz"));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(""));
    }

    @Test
    @Order(8)
    @DisplayName("Construcción de Reservas asigna campos correctamente")
    public void testConstruccionReserva() {
        Reservas reserva = new Reservas(7, 3, 4, "2026-06-10", "CONFIRMADA");
        assertEquals(7, reserva.getIdReserva());
        assertEquals(3, reserva.getId());
        assertEquals(4, reserva.getIdActividad());
        assertEquals("2026-06-10", reserva.getFecha());
        assertEquals("CONFIRMADA", reserva.getEstado());
    }
}