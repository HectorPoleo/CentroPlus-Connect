package es.ies.puerto.service;

import es.ies.puerto.model.Incidencias;
import es.ies.puerto.repository.IncidenciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciasService {

    private final IncidenciasRepository repository;

    public IncidenciasService(IncidenciasRepository repository) {
        this.repository = repository;
    }

    public List<Incidencias> findAll() {
        return repository.findAll();
    }

    public Optional<Incidencias> findById(int id) {
        return repository.findById(id);
    }

    public List<Incidencias> findByUsuario(int idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }

    public Incidencias save(Incidencias incidencia) {
        return repository.save(incidencia);
    }

    public Optional<Incidencias> update(int id, Incidencias datos) {
        return repository.findById(id).map(i -> {
            i.setIdUsuario(datos.getIdUsuario());
            i.setAsunto(datos.getAsunto());
            i.setDescripcion(datos.getDescripcion());
            i.setFecha(datos.getFecha());
            i.setEstado(datos.getEstado());
            return repository.save(i);
        });
    }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
