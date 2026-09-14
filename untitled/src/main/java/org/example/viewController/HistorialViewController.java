package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Model.Mecanico;
import org.example.Model.Orden;
import org.example.Model.Repuesto;
import org.example.Model.Tarea;
import org.example.controller.OrdenController;

import java.io.IOException;
import java.util.ArrayList;

public class HistorialViewController {

    private OrdenController ordenController = new OrdenController(Main.getTaller());

    @FXML
    private TextField txtSerial;

    @FXML
    private TableView<Orden> tablaHistorial;

    @FXML
    private TableColumn<Orden, String> colCodigo;

    @FXML
    private TableColumn<Orden, String> colFecha;

    @FXML
    private TableColumn<Orden, String> colHora;

    @FXML
    private TableColumn<Orden, String> colMotivo;

    @FXML
    private TableColumn<Orden, String> colDiagnostico;

    @FXML
    private TableColumn<Orden, String> colMecanico;

    @FXML
    private TableColumn<Orden, String> colTareas;

    @FXML
    private TableColumn<Orden, String> colRepuestos;

    @FXML
    private TableColumn<Orden, String> colCosto;


    @FXML
    private void initialize() {

        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaIngreso().toString()));

        colHora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraIngreso().toString()));

        colMotivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivoServicio()));

        colDiagnostico.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDiagnostico()));

        colMecanico.setCellValueFactory(data -> {
            ArrayList<Mecanico> mecanicos = data.getValue().getListMecanicos();

            if (mecanicos != null && !mecanicos.isEmpty()) {
                return new SimpleStringProperty(mecanicos.get(0).getNombre());
            }
            return new SimpleStringProperty("");
        });

        colTareas.setCellValueFactory(data -> {
            ArrayList<Tarea> tareas = data.getValue().getListTareas();

            if (tareas == null || tareas.isEmpty()) {
                return new SimpleStringProperty("Sin tareas");
            }
            StringBuilder texto = new StringBuilder();

            for (Tarea tarea : tareas) {
                if (texto.length() > 0) {
                    texto.append(", ");
                }
                texto.append(tarea.getNombre());
            }

            return new SimpleStringProperty(texto.toString());
        });

        colRepuestos.setCellValueFactory(data -> {

            ArrayList<Repuesto> repuestos = data.getValue().getListRepuestos();

            if (repuestos == null || repuestos.isEmpty()) {
                return new SimpleStringProperty("Sin repuestos");
            }

            StringBuilder texto = new StringBuilder();

            for (Repuesto repuesto : repuestos) {
                if (texto.length() > 0) {
                    texto.append(", ");
                }
                texto.append(repuesto.getNombre());
            }
            return new SimpleStringProperty(texto.toString());
        });

        colCosto.setCellValueFactory(data -> {
            Orden orden = data.getValue();
            int total = ordenController.getTaller().aplicarDescuentoFrecuente(orden);
            if (orden.getTheBicicleta() != null && orden.getTheBicicleta().getHistorial() != null && orden.getTheBicicleta().getHistorial().size() > 3) {
                return new SimpleStringProperty(total + " (-15%)");
            }
            return new SimpleStringProperty(String.valueOf(total));
        });
    }

    @FXML
    private void buscarHistorial() {

        String serial = txtSerial.getText().trim();

        if (serial.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Dato requerido");
            alerta.setHeaderText(null);
            alerta.setContentText("Ingresa el serial de la bicicleta.");
            alerta.showAndWait();
            return;
        }
        ArrayList<Orden> historial = ordenController.getTaller().verHistorialBicicleta(serial);

        if (historial == null || historial.isEmpty()) {
            tablaHistorial.getItems().clear();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Historial");
            alerta.setHeaderText(null);
            alerta.setContentText("No se encontraron órdenes para esta bicicleta.");
            alerta.showAndWait();

            return;
        }
        tablaHistorial.getItems().setAll(historial);
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}