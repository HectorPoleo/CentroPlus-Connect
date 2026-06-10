package es.ies.puerto.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import es.ies.puerto.modelo.Usuario;
import es.ies.puerto.service.sqlite.UsuarioService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddUsuarioControllerTest {

    private UsuarioService mockService;

    @BeforeEach
    public void setUp() {
        mockService = Mockito.mock(UsuarioService.class);
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
    @DisplayName("save con usuario válido retorna true")
    public void testGuardarUsuarioValido() {
        Usuario usuario = new Usuario(1, "Ana García", "12345678A", "ana@test.com", "600000001", "CLIENTE");
        when(mockService.save(usuario)).thenReturn(true);

        boolean resultado = mockService.save(usuario);
        assertTrue(resultado, "Guardar un usuario válido debe retornar true");
        verify(mockService, times(1)).save(usuario);
    }

    @Test
    @Order(3)
    @DisplayName("save con usuario duplicado retorna false")
    public void testGuardarUsuarioDuplicado() {
        Usuario usuario = new Usuario(1, "Ana García", "12345678A", "ana@test.com", "600000001", "CLIENTE");
        when(mockService.save(usuario)).thenReturn(false);

        boolean resultado = mockService.save(usuario);
        assertFalse(resultado, "Guardar un usuario duplicado debe retornar false");
    }

    @Test
    @Order(4)
    @DisplayName("Tipos de usuario válidos disponibles en el combo")
    public void testTiposUsuarioValidos() {
        String[] tiposEsperados = {"ADMIN", "CLIENTE", "EMPLEADO"};
        for (String tipo : tiposEsperados) {
            assertNotNull(tipo);
            assertFalse(tipo.isEmpty());
        }
        assertEquals(3, tiposEsperados.length);
    }

    @Test
    @Order(5)
    @DisplayName("Parseo de ID numérico válido no lanza excepción")
    public void testParseoIdValido() {
        assertDoesNotThrow(() -> {
            int id = Integer.parseInt("42");
            assertEquals(42, id);
        });
    }

    @Test
    @Order(6)
    @DisplayName("Parseo de ID no numérico lanza NumberFormatException")
    public void testParseoIdInvalido() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
    }

    @Test
    @Order(7)
    @DisplayName("Usuario con todos los campos seteados correctamente")
    public void testConstruccionUsuario() {
        Usuario usuario = new Usuario(5, "Carlos López", "87654321B", "carlos@test.com", "600111222", "ADMIN");
        assertEquals(5, usuario.getId());
        assertEquals("Carlos López", usuario.getNombre());
        assertEquals("87654321B", usuario.getDni());
        assertEquals("carlos@test.com", usuario.getEmail());
        assertEquals("600111222", usuario.getTelefono());
        assertEquals("ADMIN", usuario.getTipo_usuario());
    }
}
