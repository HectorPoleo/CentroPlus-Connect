package es.ies.puerto.repository;

import es.ies.puerto.model.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Integer> {
    List<Actividades> findByTipoActividad(String tipoActividad);
}
