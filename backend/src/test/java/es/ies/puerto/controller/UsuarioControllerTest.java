package es.ies.puerto.controller;

import es.ies.puerto.model.Usuario;
import es.ies.puerto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Test
    void getAll_returnsListOfUsers() throws Exception {
        Usuario u = new Usuario(1, "Ana López", "12345678A", "ana@test.com", "600000001", "socio");
        when(service.findAll()).thenReturn(List.of(u));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ana López"));
    }

    @Test
    void getById_found_returnsUser() throws Exception {
        Usuario u = new Usuario(1, "Ana López", "12345678A", "ana@test.com", "600000001", "socio");
        when(service.findById(1)).thenReturn(Optional.of(u));

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana@test.com"));
    }

    @Test
    void getById_notFound_returns404() throws Exception {
        when(service.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validUser_returns201() throws Exception {
        Usuario u = new Usuario(1, "Ana López", "12345678A", "ana@test.com", "600000001", "socio");
        when(service.save(any())).thenReturn(u);

        String json = """
            {
              "nombre": "Ana López",
              "dni": "12345678A",
              "email": "ana@test.com",
              "telefono": "600000001",
              "tipoUsuario": "socio"
            }
            """;

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void delete_existing_returns204() throws Exception {
        when(service.delete(1)).thenReturn(true);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_notExisting_returns404() throws Exception {
        when(service.delete(99)).thenReturn(false);

        mockMvc.perform(delete("/api/usuarios/99"))
                .andExpect(status().isNotFound());
    }
}
