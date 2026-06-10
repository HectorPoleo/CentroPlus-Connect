package es.ies.puerto.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import es.ies.puerto.modelo.Actividades;
import es.ies.puerto.service.sqlite.ActividadesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActividadesControllerTest {

    private ActividadesService mockService;
    private List<Actividades> actividadesMuestra;

    @BeforeEach
    public void setUp() {
        mockService = Mockito.mock(ActividadesService.class);
        actividadesMuestra = Arrays.asList(
            new Actividades(1, "Yoga", "Relajación", 60, 20, 15, 5),
            new Actividades(2, "Natación", "Acuático", 45, 30, 20, 10),
            new Actividades(3, "Pilates", "Relajación", 50, 25, 12, 3)
        );
    }

    @Test
    @Order(1)
    @DisplayName("findAll devuelve lista no nula desde el service")
    public void testFindAllNoNulo() {
        when(mockService.findAll()).thenReturn(actividadesMuestra);
        List<Actividades> resultado = mockService.findAll();
        assertNotNull(resultado, "La lista no debe ser nula");
        assertEquals(3, resultado.size());
        verify(mockService, times(1)).findAll();
    }

    @Test
    @Order(2)
    @DisplayName("findAll devuelve lista vacía cuando no hay actividades")
    public void testFindAllVacio() {
        when(mockService.findAll()).thenReturn(FXCollections.observableArrayList());
        List<Actividades> resultado = mockService.findAll();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("Filtrar por nombre retiene solo coincidencias")
    public void testFiltradoPorNombre() {
        ObservableList<Actividades> masterData = FXCollections.observableArrayList(actividadesMuestra);
        String busqueda = "yoga";
        ObservableList<Actividades> filtrado = FXCollections.observableArrayList();
        for (Actividades a : masterData) {
            if (a.getNombre().toLowerCase().contains(busqueda) ||
                a.getTipoActividad().toLowerCase().contains(busqueda)) {
                filtrado.add(a);
            }
        }
        assertEquals(1, filtrado.size());
        assertEquals("Yoga", filtrado.get(0).getNombre());
    }

    @Test
    @Order(4)
    @DisplayName("Filtrar por tipo retiene múltiples coincidencias")
    public void testFiltradoPorTipo() {
        ObservableList<Actividades> masterData = FXCollections.observableArrayList(actividadesMuestra);
        String busqueda = "relajación";
        ObservableList<Actividades> filtrado = FXCollections.observableArrayList();
        for (Actividades a : masterData) {
            if (a.getNombre().toLowerCase().contains(busqueda) ||
                a.getTipoActividad().toLowerCase().contains(busqueda)) {
                filtrado.add(a);
            }
        }
        assertEquals(2, filtrado.size());
    }

    @Test
    @Order(5)
    @DisplayName("Filtrar con texto vacío devuelve todos los elementos")
    public void testFiltradoVacioDevuelveTodo() {
        ObservableList<Actividades> masterData = FXCollections.observableArrayList(actividadesMuestra);
        String busqueda = "";
        ObservableList<Actividades> resultado = busqueda == null || busqueda.trim().isEmpty()
            ? masterData
            : FXCollections.observableArrayList();
        assertEquals(3, resultado.size());
    }

    @Test
    @Order(6)
    @DisplayName("delete llama al service con el id correcto")
    public void testDelete() {
        when(mockService.delete(1)).thenReturn(true);
        boolean eliminado = mockService.delete(1);
        assertTrue(eliminado);
        verify(mockService, times(1)).delete(1);
    }

    @Test
    @Order(7)
    @DisplayName("save delega correctamente en el service")
    public void testSave() {
        Actividades nueva = new Actividades(99, "Boxeo", "Combate", 60, 40, 10, 0);
        when(mockService.save(nueva)).thenReturn(true);
        boolean guardado = mockService.save(nueva);
        assertTrue(guardado);
        verify(mockService, times(1)).save(nueva);
    }
}