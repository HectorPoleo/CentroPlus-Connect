package es.ies.puerto.service;

import es.ies.puerto.model.Reservas;
import es.ies.puerto.repository.ReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasService {

    private final ReservasRepository repository;

    public ReservasService(ReservasRepository repository) {
        this.repository = repository;
    }

    public List<Reservas> findAll() {
        return repository.findAll();
    }

    public Optional<Reservas> findById(int id) {
        return repository.findById(id);
    }

    public List<Reservas> findByUsuario(int idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }

    public Reservas save(Reservas reserva) {
        return repository.save(reserva);
    }

    public Optional<Reservas> update(int id, Reservas datos) {
        return repository.findById(id).map(r -> {
            r.setIdUsuario(datos.getIdUsuario());
            r.setIdActividad(datos.getIdActividad());
            r.setFecha(datos.getFecha());
            r.setEstado(datos.getEstado());
            return repository.save(r);
        });
    }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
