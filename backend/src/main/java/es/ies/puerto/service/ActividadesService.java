package es.ies.puerto.service;

import es.ies.puerto.model.Actividades;
import es.ies.puerto.repository.ActividadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadesService {

    private final ActividadesRepository repository;

    public ActividadesService(ActividadesRepository repository) {
        this.repository = repository;
    }

    public List<Actividades> findAll() {
        return repository.findAll();
    }

    public Optional<Actividades> findById(int id) {
        return repository.findById(id);
    }

    public Actividades save(Actividades actividad) {
        return repository.save(actividad);
    }

    public Optional<Actividades> update(int id, Actividades datos) {
        return repository.findById(id).map(a -> {
            a.setNombre(datos.getNombre());
            a.setTipoActividad(datos.getTipoActividad());
            a.setDuracion(datos.getDuracion());
            a.setPrecio(datos.getPrecio());
            a.setPlazasMaximas(datos.getPlazasMaximas());
            a.setPlazasOcupadas(datos.getPlazasOcupadas());
            return repository.save(a);
        });
    }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
