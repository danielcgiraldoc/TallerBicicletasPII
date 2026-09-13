package org.example.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class InicioViewController {

    @FXML
    private void abrirClientes(ActionEvent event) throws IOException {
        abrirVista(event, "cliente.fxml");
    }

    @FXML
    private void abrirBicicletas(ActionEvent event) throws IOException {
        abrirVista(event, "bicicleta.fxml");
    }

    @FXML
    private void abrirMecanicos(ActionEvent event) throws IOException {
        abrirVista(event, "mecanico.fxml");
    }

    @FXML
    private void abrirNuevaOrden(ActionEvent event) throws IOException {
        abrirVista(event, "nuevaOrden.fxml");
    }

    @FXML
    private void abrirHistorial(ActionEvent event) throws IOException {
        abrirVista(event, "historial.fxml");
    }

    @FXML
    private void abrirOrdenesDia(ActionEvent event) throws IOException {
        abrirVista(event, "ordenDia.fxml");
    }
    private void abrirVista(ActionEvent event, String archivo) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/" + archivo));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}