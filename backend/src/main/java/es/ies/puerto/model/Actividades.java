package es.ies.puerto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "actividades")
public class Actividades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @Column(name = "tipo_actividad")
    private String tipoActividad;

    @Min(value = 1, message = "La duración debe ser al menos 1 minuto")
    private int duracion;

    @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
    private double precio;

    @Min(value = 1, message = "Las plazas máximas deben ser al menos 1")
    @Column(name = "plazas_maximas")
    private int plazasMaximas;

    @Column(name = "plazas_ocupadas")
    private int plazasOcupadas;

    public Actividades() {}

    public Actividades(int id, String nombre, String tipoActividad, int duracion,
                       double precio, int plazasMaximas, int plazasOcupadas) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.duracion = duracion;
        this.precio = precio;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = plazasOcupadas;
    }

    public boolean cancelarPlaza() {
        if (plazasOcupadas <= 0) return false;
        plazasOcupadas--;
        return true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(String tipoActividad) { this.tipoActividad = tipoActividad; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getPlazasMaximas() { return plazasMaximas; }
    public void setPlazasMaximas(int plazasMaximas) { this.plazasMaximas = plazasMaximas; }

    public int getPlazasOcupadas() { return plazasOcupadas; }
    public void setPlazasOcupadas(int plazasOcupadas) { this.plazasOcupadas = plazasOcupadas; }
}
