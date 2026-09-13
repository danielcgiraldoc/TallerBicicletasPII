package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BicicletaViewController {

    @FXML
    private TextField txtMarca;

    @FXML
    private ComboBox<String> cmbTipoBicicleta;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtSerial;

    @FXML
    private TextField txtAntiguedad;

    @FXML
    private void guardarBicicleta() {
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