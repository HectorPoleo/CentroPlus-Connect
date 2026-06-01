package es.ies.puerto.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void showActividades() {
        loadView("/es/ies/puerto/view/actividades.fxml");
    }

    @FXML
    public void showReservas() {
        System.out.println("Cargando reservas...");
        loadView("/es/ies/puerto/view/reservas.fxml");
    }

    @FXML
    public void showIncidencias() {
        System.out.println("Cargando incidencias...");
        loadView("/es/ies/puerto/view/incidencias.fxml");
    }

    @FXML
    public void showUsuarios() {
        loadView("/es/ies/puerto/view/usuarios.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            URL url = getClass().getResource(fxmlPath);
            System.out.println("Ruta FXML: " + url);
            if (url == null) {
                System.err.println("No se encuentra el archivo FXML en: " + fxmlPath);
                return;
            }
            FXMLLoader loader = new FXMLLoader(url);
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
            System.out.println("Vista cargada correctamente: " + fxmlPath);
        } catch (IOException e) {
            System.err.println("Error al cargar vista: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
