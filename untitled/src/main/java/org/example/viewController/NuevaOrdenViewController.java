package org.example.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.Model.Repuesto;
import org.example.Model.Tarea;

public class NuevaOrdenViewController {

    // Datos
    @FXML
    private TextField txtCodigo;

    @FXML
    private ComboBox<String> cmbBicicleta;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtHora;

    @FXML
    private ComboBox<String> cmbMecanico;

    @FXML
    private TextArea txtMotivo;

    @FXML
    private TextArea txtDiagnostico;


    // Tablas
    @FXML
    private TableView<Tarea> tablaTareas;

    @FXML
    private TableColumn<Tarea, String> colNombreTarea;

    @FXML
    private TableColumn<Tarea, String> colDescripcionTarea;

    @FXML
    private TableColumn<Tarea, Integer> colCostoTarea;

    @FXML
    private TableView<Repuesto> tablaRepuestos;

    @FXML
    private TableColumn<Repuesto, String> colNombreRepuesto;

    @FXML
    private TableColumn<Repuesto, Integer> colCantidadRepuesto;

    @FXML
    private TableColumn<Repuesto, Integer> colCostoRepuesto;

    // Botones
    @FXML
    private void agregarTarea() {
    }

    @FXML
    private void eliminarTarea() {
    }

    @FXML
    private void agregarRepuesto() {
    }

    @FXML
    private void eliminarRepuesto() {
    }

    @FXML
    private void guardarOrden() {
    }

    @FXML
    private void volverInicio() {
    }
}