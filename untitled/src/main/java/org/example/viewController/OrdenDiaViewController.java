package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Model.Bicicleta;
import org.example.Model.Cliente;
import org.example.Model.Mecanico;
import org.example.Model.Orden;
import org.example.controller.OrdenController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenDiaViewController {

    private OrdenController ordenController = new OrdenController(Main.getTaller());

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TableView<Orden> tablaOrdenes;

    @FXML
    private TableColumn<Orden, String> colCodigo;

    @FXML
    private TableColumn<Orden, String> colHora;

    @FXML
    private TableColumn<Orden, String> colBicicleta;

    @FXML
    private TableColumn<Orden, String> colMecanico;

    @FXML
    private TableColumn<Orden, String> colCliente;

    @FXML
    private TableColumn<Orden, String> colMotivo;

    @FXML
    private TableColumn<Orden, String> colEstado;


    @FXML
    private void initialize() {

        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

        colHora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraIngreso().toString()));

        colBicicleta.setCellValueFactory(data -> {

            Bicicleta bicicleta = data.getValue().getTheBicicleta();

            if (bicicleta != null) {
                return new SimpleStringProperty(
                        bicicleta.getSerial()
                );
            }
            return new SimpleStringProperty("");
        });

        colMecanico.setCellValueFactory(data -> {

            ArrayList<Mecanico> mecanicos = data.getValue().getListMecanicos();

            if (mecanicos != null && !mecanicos.isEmpty()) {
                return new SimpleStringProperty(mecanicos.get(0).getNombre());
            }
            return new SimpleStringProperty("");
        });

        colCliente.setCellValueFactory(data -> {

            Bicicleta bicicleta = data.getValue().getTheBicicleta();

            if (bicicleta != null) {

                Cliente cliente = bicicleta.getTheCliente();

                if (cliente != null) {
                    return new SimpleStringProperty(
                            cliente.getNombre()
                    );
                }
            }
            return new SimpleStringProperty("");
        });

        colMotivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivoServicio()));

        colEstado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstado().toString()));
    }

    @FXML
    private void buscarOrdenes() {

        LocalDate fecha = dpFecha.getValue();

        if (fecha == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Fecha requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una fecha para realizar la búsqueda.");
            alerta.showAndWait();

            return;
        }

        ArrayList<Orden> ordenes = ordenController.getTaller().consultarOrdenesPorDia(fecha);

        if (ordenes == null || ordenes.isEmpty()) {

            tablaOrdenes.getItems().clear();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Órdenes del día");
            alerta.setHeaderText(null);
            alerta.setContentText("No se encontraron órdenes para la fecha seleccionada.");
            alerta.showAndWait();

            return;
        }
        tablaOrdenes.getItems().setAll(ordenes);
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