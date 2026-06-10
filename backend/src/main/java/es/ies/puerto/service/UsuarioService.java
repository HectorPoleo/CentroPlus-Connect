package es.ies.puerto.service;

import es.ies.puerto.model.Usuario;
import es.ies.puerto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return repository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> update(int id, Usuario datos) {
        return repository.findById(id).map(u -> {
            u.setNombre(datos.getNombre());
            u.setDni(datos.getDni());
            u.setEmail(datos.getEmail());
            u.setTelefono(datos.getTelefono());
            u.setTipoUsuario(datos.getTipoUsuario());
            return repository.save(u);
        });
    }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
