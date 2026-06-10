package es.ies.puerto.repository;

import es.ies.puerto.model.Incidencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciasRepository extends JpaRepository<Incidencias, Integer> {
    List<Incidencias> findByIdUsuario(int idUsuario);
    List<Incidencias> findByEstado(String estado);
}
