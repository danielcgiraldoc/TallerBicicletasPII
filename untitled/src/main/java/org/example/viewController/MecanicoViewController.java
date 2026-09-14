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
import org.example.controller.MecanicoController;

import java.io.IOException;

public class MecanicoViewController {

    private MecanicoController mecanicoController = new MecanicoController(Main.getTaller());

    @FXML
    private TextField txtNombreMecanico;

    @FXML
    private ComboBox<String> cmbEspecialidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private void guardarMecanico() {

        String nombre = txtNombreMecanico.getText();
        String codigo = txtCodigo.getText();

        if (nombre.isBlank() ||
                codigo.isBlank() ||
                cmbEspecialidad.getValue() == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa todos los campos.");
            alerta.showAndWait();
            return;
        }

        boolean registrado = mecanicoController.registrarMecanico(nombre, codigo, true);

        if (registrado) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mecánico registrado");
            alerta.setHeaderText(null);
            alerta.setContentText("El mecánico se registró correctamente.");
            alerta.showAndWait();

            txtNombreMecanico.clear();
            txtCodigo.clear();
            cmbEspecialidad.setValue(null);

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Ya existe un mecánico con ese código.");
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