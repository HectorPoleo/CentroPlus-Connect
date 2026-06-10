package es.ies.puerto.repository;

import es.ies.puerto.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    List<Reservas> findByIdUsuario(int idUsuario);
    List<Reservas> findByIdActividad(int idActividad);
    List<Reservas> findByEstado(String estado);
}
