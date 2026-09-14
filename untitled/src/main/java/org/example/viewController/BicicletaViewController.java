package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Model.Bicicleta;
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
    private TableView<Bicicleta> tablaBicicletas;

    @FXML
    private TableColumn<Bicicleta, String> colMarca;

    @FXML
    private TableColumn<Bicicleta, String> colTipo;

    @FXML
    private TableColumn<Bicicleta, String> colColor;

    @FXML
    private TableColumn<Bicicleta, String> colSerial;

    @FXML
    private TableColumn<Bicicleta, String> colAntiguedad;

    @FXML
    private TableColumn<Bicicleta, String> colCliente;

    private Bicicleta bicicletaEditar;

    @FXML
    private void initialize() {

        colMarca.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getMarca()));

        colTipo.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTipoBicicleta().toString()));

        colColor.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getColor()));

        colSerial.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getSerial()));

        colAntiguedad.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getAntiguedad())));

        colCliente.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getTheCliente() != null
                                ? data.getValue().getTheCliente().getNombre()
                                : ""
                ));

        cargarBicicletas();
    }

    private void cargarBicicletas() {
        tablaBicicletas.getItems().setAll(
                bicicletaController.getTaller().getListBicicletas()
                        .stream()
                        .filter(bicicleta -> bicicleta != null)
                        .toList()
        );
    }

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

        if (bicicletaEditar != null) {
            boolean actualizado = bicicletaController.actualizarBicicleta(marca, color, serial, antiguedad, tipo);

            if (actualizado) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Bicicleta actualizada");
                alerta.setHeaderText(null);
                alerta.setContentText("La bicicleta se actualizó correctamente.");
                alerta.showAndWait();

                bicicletaEditar = null;

                txtMarca.clear();
                txtIdCliente.clear();
                txtColor.clear();
                txtSerial.clear();
                txtAntiguedad.clear();
                cmbTipoBicicleta.setValue(null);

                cargarBicicletas();
            }
            return;
        }

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

            cargarBicicletas();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo registrar la bicicleta. " + "Verifica que el cliente exista y que el serial no esté registrado.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void editarBicicleta() {

        Bicicleta seleccionada = tablaBicicletas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Bicicleta no seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una bicicleta de la tabla.");
            alerta.showAndWait();
            return;
        }

        bicicletaEditar = seleccionada;

        txtMarca.setText(seleccionada.getMarca());
        txtIdCliente.setText(seleccionada.getTheCliente() != null ? seleccionada.getTheCliente().getId() : "");
        txtColor.setText(seleccionada.getColor());
        txtSerial.setText(seleccionada.getSerial());
        txtAntiguedad.setText(String.valueOf(seleccionada.getAntiguedad()));
        cmbTipoBicicleta.setValue(seleccionada.getTipoBicicleta().toString());
    }

    @FXML
    private void eliminarBicicleta() {

        Bicicleta seleccionada = tablaBicicletas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Bicicleta no seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una bicicleta de la tabla.");
            alerta.showAndWait();
            return;
        }

        boolean eliminado = bicicletaController.elimarBicicleta(seleccionada.getSerial());

        if (eliminado) {
            tablaBicicletas.getItems().remove(seleccionada);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Bicicleta eliminada");
            alerta.setHeaderText(null);
            alerta.setContentText("La bicicleta se eliminó correctamente.");
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