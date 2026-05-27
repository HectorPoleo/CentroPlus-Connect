package es.ies.puerto.modelo;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncidenciasTest {
    Incidencias incidencia;
    int idIncidencia = 1;
    int idUsuario = 2;
    String asunto = "Averia";
    String descripcion = "Televisor averiado";
    String fecha = LocalDate.now().toString();
    String estado = "En Proceso";

    @BeforeEach
    void setup(){
        incidencia = new Incidencias(idIncidencia, idUsuario, asunto, descripcion, fecha, estado);
    }
    
    @Test
    void incidenciaNotNull(){
        Assertions.assertNotNull(asunto);
    }

    @Test
    void IncidenciasEqualsTrueTest(){
        Incidencias incidenciaNueva = new Incidencias(1);
        Assertions.assertEquals(incidencia, incidenciaNueva);
    }


}
