package org.example.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.Repuesto;

public class RepuestoViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCosto;

    private String nombre;
    private int cantidad;
    private int costo;
    private Repuesto repuestoEditar;

    @FXML
    private void guardarRepuesto() {

        if (txtNombre.getText().isBlank() || txtCantidad.getText().isBlank() || txtCosto.getText().isBlank()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa todos los campos.");
            alerta.showAndWait();
            return;
        }

        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            costo = Integer.parseInt(txtCosto.getText());

            if (cantidad <= 0 || costo < 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos inválidos");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "La cantidad debe ser mayor que 0 y el costo debe ser un número válido.");
            alerta.showAndWait();
            return;
        }
        nombre = txtNombre.getText();
        if (repuestoEditar != null) {
            repuestoEditar.setNombre(nombre);
            repuestoEditar.setCantidad(cantidad);
            repuestoEditar.setCosto(costo);
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

    public int getCantidad() {
        return cantidad;
    }

    public int getCosto() {
        return costo;
    }

    public void prepararEdicion(Repuesto repuesto) {
        repuestoEditar = repuesto;
        txtNombre.setText(repuesto.getNombre());
        txtCantidad.setText(String.valueOf(repuesto.getCantidad()));
        txtCosto.setText(String.valueOf(repuesto.getCosto()));
    }
}