package org.example.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.Main;
import org.example.Model.Cliente;
import org.example.controller.ClienteController;

import java.io.IOException;

public class ClienteViewController {

        private ClienteController clienteController = new ClienteController(Main.getTaller());

        @FXML
        private TextField txtNombre;

        @FXML
        private TextField txtIdentificacion;

        @FXML
        private TextField txtTelefono;

        @FXML
        private TextField txtDireccion;

        @FXML
        private TableView<Cliente> tablaClientes;

        @FXML
        private TableColumn<Cliente, String> colNombre;

        @FXML
        private TableColumn<Cliente, String> colIdentificacion;

        @FXML
        private TableColumn<Cliente, String> colTelefono;

        @FXML
        private TableColumn<Cliente, String> colDireccion;

        private Cliente clienteEditar;

        @FXML
        private void initialize() {

                colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

                colIdentificacion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));

                colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));

                colDireccion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDireccion()));

                cargarClientes();
        }

        private void cargarClientes() {
                tablaClientes.getItems().setAll(clienteController.getTaller().getListClientes().stream().filter(cliente -> cliente != null).toList());
        }

        @FXML
        private void guardarCliente() {
                String nombre = txtNombre.getText();
                String id = txtIdentificacion.getText();
                String telefono = txtTelefono.getText();
                String direccion = txtDireccion.getText();

                if (nombre.isBlank() || id.isBlank() || telefono.isBlank() || direccion.isBlank()) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Datos incompletos");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Por favor, completa todos los campos.");
                        alerta.showAndWait();
                        return;
                }
                if (clienteEditar != null) {

                        boolean actualizado = clienteController.actualizarCliente(nombre, id, telefono);

                        if (actualizado) {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Cliente actualizado");
                                alerta.setHeaderText(null);
                                alerta.setContentText("El cliente se actualizó correctamente.");
                                alerta.showAndWait();

                                clienteEditar = null;

                                txtNombre.clear();
                                txtIdentificacion.clear();
                                txtTelefono.clear();
                                txtDireccion.clear();

                                cargarClientes();
                        }
                        return;
                }

                boolean registrado = clienteController.registrarCliente(nombre, id, telefono, direccion);

                if (registrado) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Cliente registrado");
                        alerta.setHeaderText(null);
                        alerta.setContentText("El cliente se registró correctamente.");
                        alerta.showAndWait();
                        txtNombre.clear();
                        txtIdentificacion.clear();
                        txtTelefono.clear();
                        txtDireccion.clear();

                        cargarClientes();
                } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Ya existe un cliente con esa identificación.");
                        alerta.showAndWait();
                }
        }

        @FXML
        private void editarCliente() {

                Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

                if (seleccionado == null) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Cliente no seleccionado");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Selecciona un cliente de la tabla.");
                        alerta.showAndWait();
                        return;
                }

                clienteEditar = seleccionado;

                txtNombre.setText(seleccionado.getNombre());
                txtIdentificacion.setText(seleccionado.getId());
                txtTelefono.setText(seleccionado.getTelefono());
                txtDireccion.setText(seleccionado.getDireccion());
        }

        @FXML
        private void eliminarCliente() {

                Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

                if (seleccionado == null) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Cliente no seleccionado");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Selecciona un cliente de la tabla.");
                        alerta.showAndWait();
                        return;
                }

                boolean eliminado = clienteController.eliminarCliente(seleccionado.getId());

                if (eliminado) {
                        tablaClientes.getItems().remove(seleccionado);

                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Cliente eliminado");
                        alerta.setHeaderText(null);
                        alerta.setContentText("El cliente se eliminó correctamente.");
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