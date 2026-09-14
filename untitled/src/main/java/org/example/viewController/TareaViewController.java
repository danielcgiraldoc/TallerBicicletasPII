package org.example.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.Tarea;

public class TareaViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtCosto;

    private String nombre;
    private String descripcion;
    private int costo;
    private Tarea tareaEditar;

    @FXML
    private void guardarTarea() {

        if (txtNombre.getText().isBlank() || txtDescripcion.getText().isBlank() || txtCosto.getText().isBlank()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa todos los campos.");
            alerta.showAndWait();
            return;
        }

        try {
            costo = Integer.parseInt(txtCosto.getText());
        } catch (NumberFormatException e) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Dato inválido");
            alerta.setHeaderText(null);
            alerta.setContentText("El costo debe ser un número.");
            alerta.showAndWait();
            return;
        }
        nombre = txtNombre.getText();
        descripcion = txtDescripcion.getText();
        if (tareaEditar != null) {
            tareaEditar.setNombre(nombre);
            tareaEditar.setDescripcion(descripcion);
            tareaEditar.setCosto(costo);
        }
        cerrarVentana();
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage ventana = (Stage) txtNombre.getScene().getWindow();
        ventana.close();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void prepararEdicion(Tarea tarea) {
        tareaEditar = tarea;
        txtNombre.setText(tarea.getNombre());
        txtDescripcion.setText(tarea.getDescripcion());
        txtCosto.setText(String.valueOf(tarea.getCosto()));
    }
}