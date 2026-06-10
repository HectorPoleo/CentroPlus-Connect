package es.ies.puerto.service;

import es.ies.puerto.model.Usuario;
import es.ies.puerto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_returnsAllUsers() {
        when(repository.findAll()).thenReturn(List.of(new Usuario()));
        assertEquals(1, service.findAll().size());
    }

    @Test
    void findById_existing_returnsUser() {
        Usuario u = new Usuario(1, "Test", "12345678T", "t@t.com", "600000000", "socio");
        when(repository.findById(1)).thenReturn(Optional.of(u));
        assertTrue(service.findById(1).isPresent());
    }

    @Test
    void findById_notExisting_returnsEmpty() {
        when(repository.findById(99)).thenReturn(Optional.empty());
        assertTrue(service.findById(99).isEmpty());
    }

    @Test
    void save_callsRepository() {
        Usuario u = new Usuario(1, "Test", "12345678T", "t@t.com", "600000000", "socio");
        when(repository.save(u)).thenReturn(u);
        assertEquals(u, service.save(u));
        verify(repository, times(1)).save(u);
    }

    @Test
    void delete_existing_returnsTrue() {
        when(repository.existsById(1)).thenReturn(true);
        assertTrue(service.delete(1));
        verify(repository).deleteById(1);
    }

    @Test
    void delete_notExisting_returnsFalse() {
        when(repository.existsById(99)).thenReturn(false);
        assertFalse(service.delete(99));
        verify(repository, never()).deleteById(99);
    }
}
