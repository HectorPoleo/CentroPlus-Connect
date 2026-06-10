package es.ies.puerto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "reservas")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "id_actividad", nullable = false)
    private int idActividad;

    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public Reservas() {}

    public Reservas(int idReserva, int idUsuario, int idActividad, String fecha, String estado) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
