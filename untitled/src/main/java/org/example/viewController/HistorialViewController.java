package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Model.Orden;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

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
    private void volverInicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}