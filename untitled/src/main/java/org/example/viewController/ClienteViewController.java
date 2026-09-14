package org.example.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Main;
import org.example.controller.ClienteController;

import java.io.IOException;

public class ClienteViewController {

        private ClienteController clienteController = new ClienteController(Main.getTaller());

        @FXML
        private TextField txtNombre;

        @FXML
        private TextField txtIdentificacion;

        @FXML
        private TextField txtTelefono;

        @FXML
        private TextField txtDireccion;

        @FXML
        private void guardarCliente() {
                String nombre = txtNombre.getText();
                String id = txtIdentificacion.getText();
                String telefono = txtTelefono.getText();
                String direccion = txtDireccion.getText();

                if (nombre.isBlank() || id.isBlank() || telefono.isBlank() || direccion.isBlank()) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Datos incompletos");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Por favor, completa todos los campos.");
                        alerta.showAndWait();
                        return;
                }
                boolean registrado = clienteController.registrarCliente(nombre, id, telefono, direccion);

                if (registrado) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Cliente registrado");
                        alerta.setHeaderText(null);
                        alerta.setContentText("El cliente se registró correctamente.");
                        alerta.showAndWait();
                        txtNombre.clear();
                        txtIdentificacion.clear();
                        txtTelefono.clear();
                        txtDireccion.clear();
                } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Ya existe un cliente con esa identificación.");
                        alerta.showAndWait();
                }
        }

        @FXML
        private void volverInicio(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("/org.example/inicio.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        }
    }
