package es.ies.puerto.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import es.ies.puerto.modelo.Actividades;

class ActividadesTest {
    Actividades actividad;
    int id = 1;
    String nombre = "Piscina";
    String tipo = "Acuatico";
    int duracion = 2;
    double precio = 30.5;
    int plazas_maximas = 16;
    int plazas_ocupadas = 15;

    @BeforeEach
    void setup(){
        actividad = new Actividades(id, nombre, tipo, duracion, precio, plazas_maximas, plazas_ocupadas);
    }

    @DisplayName("Test verifica no sea nulo")
    @Order(1)
    @Test
    void actividadNotNullTest(){
        Assertions.assertNotNull(actividad, "La clase actividad no puede ser nulo");
    }

    @DisplayName("Test que verifica que sean iguales")
    @Order(2)
    @Test
    void actividadEqualsTrueTest(){
        Actividades actividadNueva = new Actividades(1);
        Assertions.assertEquals(actividad, actividadNueva, "Deben de ser igual");
    }

    @DisplayName("Test que verifica que no sean iguales")
    @Order(3)
    @Test
    void actividadEqualsNotTrueTest(){
        Actividades actividadNueva = new Actividades(2);
        Assertions.assertNotEquals(actividad, actividadNueva, "Deben de ser igual");
    }

    @DisplayName("Test que verifica que sean igual")
    @Order(4)
    @Test
    void actividadEqualsTest(){
        Assertions.assertEquals(actividad, actividad, "Deben de ser igual");
    }

    @DisplayName("Test que ")
    @Order(5)
    @Test
    void actividadPlazasDisponibles(){
        actividad.cancelarPlaza();
        Assertions.assertEquals(plazas_maximas-plazas_ocupadas+1, actividad.getPlazas_ocupadas());
    }
    
}
