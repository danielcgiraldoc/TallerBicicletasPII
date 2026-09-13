package org.example.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteViewController {

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
