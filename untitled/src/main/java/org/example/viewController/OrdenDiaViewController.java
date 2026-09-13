package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Model.Orden;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

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
    private void volverInicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}