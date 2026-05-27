package es.ies.puerto.modelo;

import java.util.Objects;

public class Reservas extends Usuario{
    int idReserva;
    int idActividad;
    String fecha;
    String estado;

    public Reservas() {
        super();
    }

    public Reservas(int idReserva) {
        this.idReserva = idReserva;
    }

    public Reservas(int idReserva, int idUsuario, int idActividad, String fecha, String estado) {
        super(idUsuario);
        this.idReserva = idReserva;
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdReserva() {
        return this.idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }


    public int getIdActividad() {
        return this.idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(idReserva);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservas other = (Reservas) obj;
        return idReserva == other.idReserva;
    }

    @Override
    public String toString() {
        return "{" +
            " idReserva='" + getIdReserva() + "'" +
            ", idUsuario='" + getId() + "'" +
            ", idActividad='" + getIdActividad() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }

}
