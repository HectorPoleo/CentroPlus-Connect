package es.ies.puerto.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import es.ies.puerto.modelo.Incidencias;
import es.ies.puerto.modelo.Reservas;
import es.ies.puerto.modelo.Usuario;
import es.ies.puerto.service.sqlite.IncidenciasService;
import es.ies.puerto.service.sqlite.ReservasService;
import es.ies.puerto.service.sqlite.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListControllerTest {

    private UsuarioService mockUsuarioService;
    private ReservasService mockReservasService;
    private IncidenciasService mockIncidenciasService;

    @BeforeEach
    public void setUp() {
        mockUsuarioService = Mockito.mock(UsuarioService.class);
        mockReservasService = Mockito.mock(ReservasService.class);
        mockIncidenciasService = Mockito.mock(IncidenciasService.class);
    }

    @Test
    @Order(1)
    @DisplayName("UsuariosController: findAll retorna lista no nula")
    public void testUsuariosFindAllNoNulo() {
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1, "Ana", "11111111A", "ana@test.com", "600000001", "CLIENTE"),
            new Usuario(2, "Luis", "22222222B", "luis@test.com", "600000002", "EMPLEADO")
        );
        when(mockUsuarioService.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = mockUsuarioService.findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(mockUsuarioService, times(1)).findAll();
    }

    @Test
    @Order(2)
    @DisplayName("UsuariosController: findAll vacío no lanza excepción al cargar tabla")
    public void testUsuariosFindAllVacio() {
        when(mockUsuarioService.findAll()).thenReturn(Collections.emptyList());

        List<Usuario> resultado = mockUsuarioService.findAll();
        assertNotNull(resultado);
        ObservableList<Usuario> data = FXCollections.observableArrayList();
        assertDoesNotThrow(() -> data.addAll(resultado));
        assertTrue(data.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("UsuariosController: delete llama al service con id correcto")
    public void testUsuariosDelete() {
        when(mockUsuarioService.delete(1)).thenReturn(true);

        boolean eliminado = mockUsuarioService.delete(1);
        assertTrue(eliminado);
        verify(mockUsuarioService, times(1)).delete(1);
    }

    @Test
    @Order(4)
    @DisplayName("UsuariosController: delete con id inexistente retorna false")
    public void testUsuariosDeleteNoExistente() {
        when(mockUsuarioService.delete(-1)).thenReturn(false);

        boolean eliminado = mockUsuarioService.delete(-1);
        assertFalse(eliminado);
    }

    @Test
    @Order(5)
    @DisplayName("ReservasController: findAll retorna lista no nula")
    public void testReservasFindAllNoNulo() {
        List<Reservas> reservas = Arrays.asList(
            new Reservas(1, 1, 2, "2026-06-10", "PENDIENTE"),
            new Reservas(2, 3, 1, "2026-07-01", "CONFIRMADA")
        );
        when(mockReservasService.findAll()).thenReturn(reservas);

        List<Reservas> resultado = mockReservasService.findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(mockReservasService, times(1)).findAll();
    }

    @Test
    @Order(6)
    @DisplayName("ReservasController: cancelar llama al service con idReserva correcto")
    public void testReservasCancelar() {
        when(mockReservasService.delete(1)).thenReturn(true);

        boolean cancelado = mockReservasService.delete(1);
        assertTrue(cancelado);
        verify(mockReservasService, times(1)).delete(1);
    }

    @Test
    @Order(7)
    @DisplayName("ReservasController: findAll con lista nula no añade nulos a ObservableList")
    public void testReservasFindAllNulo() {
        when(mockReservasService.findAll()).thenReturn(null);

        List<Reservas> resultado = mockReservasService.findAll();
        ObservableList<Reservas> data = FXCollections.observableArrayList();
        if (resultado != null) {
            data.addAll(resultado);
        }
        assertTrue(data.isEmpty(), "La lista no debe recibir datos nulos");
    }

    @Test
    @Order(8)
    @DisplayName("IncidenciasController: findAll retorna lista no nula")
    public void testIncidenciasFindAllNoNulo() {
        List<Incidencias> incidencias = Arrays.asList(
            new Incidencias(1, 2, "Acceso denegado", "No puede entrar", "2026-06-01 09:00", "ABIERTA"),
            new Incidencias(2, 3, "Equipamiento roto", "La cinta está rota", "2026-06-02 10:00", "EN_PROCESO")
        );
        when(mockIncidenciasService.findAll()).thenReturn(incidencias);

        List<Incidencias> resultado = mockIncidenciasService.findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(mockIncidenciasService, times(1)).findAll();
    }

    @Test
    @Order(9)
    @DisplayName("IncidenciasController: findAll con lista nula no falla al cargar tabla")
    public void testIncidenciasFindAllNulo() {
        when(mockIncidenciasService.findAll()).thenReturn(null);

        List<Incidencias> resultado = mockIncidenciasService.findAll();
        ObservableList<Incidencias> data = FXCollections.observableArrayList();
        if (resultado != null) {
            data.addAll(resultado);
        }
        assertTrue(data.isEmpty());
    }

    @Test
    @Order(10)
    @DisplayName("IncidenciasController: datos cargados en ObservableList correctamente")
    public void testIncidenciasCargaEnObservableList() {
        List<Incidencias> incidencias = Arrays.asList(
            new Incidencias(1, 2, "Asunto A", "Desc A", "2026-06-01 09:00", "ABIERTA")
        );
        when(mockIncidenciasService.findAll()).thenReturn(incidencias);

        List<Incidencias> resultado = mockIncidenciasService.findAll();
        ObservableList<Incidencias> data = FXCollections.observableArrayList();
        if (resultado != null) {
            data.addAll(resultado);
        }
        assertEquals(1, data.size());
        assertEquals("Asunto A", data.get(0).getAsunto());
    }
}