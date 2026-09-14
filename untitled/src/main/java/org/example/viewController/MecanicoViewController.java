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
import org.example.Model.Mecanico;
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
    private TableView<Mecanico> tablaMecanicos;

    @FXML
    private TableColumn<Mecanico, String> colNombre;

    @FXML
    private TableColumn<Mecanico, String> colEspecialidad;

    @FXML
    private TableColumn<Mecanico, String> colCodigo;

    @FXML
    private TableColumn<Mecanico, String> colDisponible;

    private Mecanico mecanicoEditar;

    @FXML
    private void initialize() {

        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        colEspecialidad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEspecialidad() != null ? data.getValue().getEspecialidad().toString() : ""));

        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

        colDisponible.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDisponible() ? "Sí" : "No"));

        cargarMecanicos();
    }

    private void cargarMecanicos() {
        tablaMecanicos.getItems().setAll(mecanicoController.getTaller().getListMecanicos().stream().filter(mecanico -> mecanico != null).toList());
    }

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
        if (mecanicoEditar != null) {

            boolean actualizado = mecanicoController.actualizarMecanico(nombre, codigo, mecanicoEditar.getDisponible());

            if (actualizado) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Mecánico actualizado");
                alerta.setHeaderText(null);
                alerta.setContentText("El mecánico se actualizó correctamente.");
                alerta.showAndWait();

                mecanicoEditar = null;

                txtNombreMecanico.clear();
                txtCodigo.clear();
                cmbEspecialidad.setValue(null);

                cargarMecanicos();
            }

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

            cargarMecanicos();

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Ya existe un mecánico con ese código.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void editarMecanico() {

        Mecanico seleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Mecánico no seleccionado");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona un mecánico de la tabla.");
            alerta.showAndWait();
            return;
        }

        mecanicoEditar = seleccionado;

        txtNombreMecanico.setText(seleccionado.getNombre());
        txtCodigo.setText(seleccionado.getCodigo());

        if (seleccionado.getEspecialidad() != null) {
            cmbEspecialidad.setValue(seleccionado.getEspecialidad().toString());
        }
    }

    @FXML
    private void eliminarMecanico() {

        Mecanico seleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Mecánico no seleccionado");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona un mecánico de la tabla.");
            alerta.showAndWait();
            return;
        }

        boolean eliminado = mecanicoController.eliminarMecanico(seleccionado.getCodigo());

        if (eliminado) {
            tablaMecanicos.getItems().remove(seleccionado);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mecánico eliminado");
            alerta.setHeaderText(null);
            alerta.setContentText("El mecánico se eliminó correctamente.");
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