package es.ies.puerto.modelo;

import java.util.Objects;

public class Actividades {

    int id;
    String nombre;
    String tipoActividad;
    int duracion;
    int precio;
    int plazas_maximas;
    int plazas_ocupadas;

    public Actividades(){

    }

    public Actividades(int id, String nombre, String tipoActividad, int duracion, int precio, int plazas_maximas, int plazas_ocupadas) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.duracion = duracion;
        this.precio = precio;
        this.plazas_maximas = plazas_maximas;
        this.plazas_ocupadas = plazas_ocupadas;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoActividad() {
        return this.tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPlazas_maximas() {
        return this.plazas_maximas;
    }

    public void setPlazas_maximas(int plazas_maximas) {
        this.plazas_maximas = plazas_maximas;
    }

    public int getPlazas_ocupadas() {
        return this.plazas_ocupadas;
    }

    public void setPlazas_ocupadas(int plazas_ocupadas) {
        this.plazas_ocupadas = plazas_ocupadas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Actividades other = (Actividades) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", tipoActividad='" + getTipoActividad() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", plazas_maximas='" + getPlazas_maximas() + "'" +
            ", plazas_ocupadas='" + getPlazas_ocupadas() + "'" +
            "}";
    }
}
