package es.ies.puerto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import es.ies.puerto.connection.SQLiteConnectionManager;
import es.ies.puerto.modelo.Incidencias;
import es.ies.puerto.service.sqlite.IncidenciasService;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IncidenciasServiceTest {

    private static IncidenciasService incidenciasService;

    @BeforeAll
    public static void setUp() {
        SQLiteConnectionManager.setDatabasePath("src/test/resources/centroplus-test.db");
        incidenciasService = new IncidenciasService();
    }

    @Test
    @Order(1)
    public void testFindAll() {
        List<Incidencias> incidencias = incidenciasService.findAll();
        assertNotNull(incidencias);
        System.out.println("Número de incidencias encontradas: " + incidencias.size());
    }

    @Test
    @Order(2)
    public void testSaveAndFindById() {
        Incidencias incidencia = new Incidencias(999, 1, "Prueba Asunto", "Prueba Descripción", "2026-12-31", "Abierta");
        boolean guardado = incidenciasService.save(incidencia);
        assertTrue(guardado);

        Incidencias encontrada = incidenciasService.findById(999);
        assertNotNull(encontrada);
        assertEquals("Prueba Asunto", encontrada.getAsunto());
    }

    @Test
    @Order(3)
    public void testUpdate() {
        Incidencias incidencia = incidenciasService.findById(999);
        incidencia.setEstado("Cerrada");
        boolean actualizado = incidenciasService.update(incidencia);
        assertTrue(actualizado);

        Incidencias actualizada = incidenciasService.findById(999);
        assertEquals("Cerrada", actualizada.getEstado());
    }

    @Test
    @Order(4)
    public void testDelete() {
        boolean eliminado = incidenciasService.delete(999);
        assertTrue(eliminado);

        Incidencias eliminada = incidenciasService.findById(999);
        assertNull(eliminada);
    }
}
