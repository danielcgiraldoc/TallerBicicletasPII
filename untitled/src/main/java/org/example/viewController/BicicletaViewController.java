package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Model.TipoBicicleta;
import org.example.controller.BicicletaController;

import java.io.IOException;

public class BicicletaViewController {

    private BicicletaController bicicletaController = new BicicletaController(Main.getTaller());

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtIdCliente;

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

        String marca = txtMarca.getText();
        String idCliente = txtIdCliente.getText();
        String color = txtColor.getText();
        String serial = txtSerial.getText();

        if (marca.isBlank() ||
                idCliente.isBlank() ||
                color.isBlank() ||
                serial.isBlank() ||
                txtAntiguedad.getText().isBlank() ||
                cmbTipoBicicleta.getValue() == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa todos los campos.");
            alerta.showAndWait();
            return;
        }

        int antiguedad;

        try {
            antiguedad = Integer.parseInt(txtAntiguedad.getText());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Dato inválido");
            alerta.setHeaderText(null);
            alerta.setContentText("La antigüedad debe ser un número.");
            alerta.showAndWait();
            return;
        }

        TipoBicicleta tipo = TipoBicicleta.valueOf(cmbTipoBicicleta.getValue());

        boolean registrada = bicicletaController.registrarBicicleta(marca, color, serial, antiguedad, tipo, idCliente);

        if (registrada) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Bicicleta registrada");
            alerta.setHeaderText(null);
            alerta.setContentText("La bicicleta se registró correctamente.");
            alerta.showAndWait();

            txtMarca.clear();
            txtIdCliente.clear();
            txtColor.clear();
            txtSerial.clear();
            txtAntiguedad.clear();
            cmbTipoBicicleta.setValue(null);

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo registrar la bicicleta. " + "Verifica que el cliente exista y que el serial no esté registrado.");
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