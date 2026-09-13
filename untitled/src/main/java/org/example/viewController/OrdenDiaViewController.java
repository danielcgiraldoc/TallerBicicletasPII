package org.example.viewController;

import org.example.Model.Orden;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdenDiaViewController {

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
    private void buscarOrdenes() {
    }

    @FXML
    private void volverInicio() {
    }
}