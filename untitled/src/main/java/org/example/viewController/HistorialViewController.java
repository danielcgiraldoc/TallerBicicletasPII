package org.example.viewController;

import org.example.Model.Orden;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HistorialViewController {

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
    private void buscarHistorial() {
    }

    @FXML
    private void volverInicio() {
    }
}