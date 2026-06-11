package es.ies.puerto.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI centroPlusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CentroPlus Connect API")
                        .description("""
                                ## 🏋️ API REST del sistema CentroPlus Connect
                                
                                Gestión integral de un centro deportivo: usuarios, actividades, reservas e incidencias.
                                
                                ### Módulos disponibles
                                - **Usuarios** — Alta, baja y consulta de socios
                                - **Actividades** — Catálogo de clases y sus plazas
                                - **Reservas** — Vinculación usuario ↔ actividad con estado
                                - **Incidencias** — Registro y seguimiento de problemas
                                
                                > Base de datos: SQLite · Framework: Spring Boot 3.3 · Java 17
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("IES Puerto de la Cruz")
                                .email("centroplus@ies-puerto.es"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8090").description("Servidor local de desarrollo")
                ))
                .tags(List.of(
                        new Tag().name("Usuarios").description("Gestión de socios y miembros del centro"),
                        new Tag().name("Actividades").description("Catálogo de actividades y control de plazas"),
                        new Tag().name("Reservas").description("Reservas de actividades por usuario"),
                        new Tag().name("Incidencias").description("Registro y seguimiento de incidencias")
                ));
    }
}
