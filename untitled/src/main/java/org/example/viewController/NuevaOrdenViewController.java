package org.example.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Model.Bicicleta;
import org.example.Model.EstadoOrden;
import org.example.Model.Mecanico;
import org.example.Model.Orden;
import org.example.Model.Repuesto;
import org.example.Model.Tarea;
import org.example.controller.OrdenController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class NuevaOrdenViewController {

    private OrdenController ordenController = new OrdenController(Main.getTaller());
    private ObservableList<Tarea> tareas = FXCollections.observableArrayList();
    private ObservableList<Repuesto> repuestos = FXCollections.observableArrayList();

    private Orden ordenEditar;

    @FXML
    private void initialize() {
        cmbBicicleta.setItems(FXCollections.observableArrayList(ordenController.obtenerBicicletas().stream().map(Bicicleta::getSerial).toList()));

        cmbMecanico.setItems(FXCollections.observableArrayList(ordenController.obtenerMecanicos().stream().map(Mecanico::getCodigo).toList()));

        tablaTareas.setItems(tareas);

        colNombreTarea.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        colDescripcionTarea.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));

        colCostoTarea.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCosto()));

        tablaTareas.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Tarea tareaSeleccionada = tablaTareas.getSelectionModel().getSelectedItem();
                if (tareaSeleccionada != null) {
                    try {
                        editarTarea(tareaSeleccionada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tablaRepuestos.setItems(repuestos);

        colNombreRepuesto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        colCantidadRepuesto.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCantidad()));

        colCostoRepuesto.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCosto()));

        tablaRepuestos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Repuesto repuestoSeleccionado = tablaRepuestos.getSelectionModel().getSelectedItem();
                if (repuestoSeleccionado != null) {
                    try {
                        editarRepuesto(repuestoSeleccionado);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        colCodigoOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

        colFechaOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaIngreso().toString()));

        colHoraOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraIngreso().toString()));

        colBicicletaOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTheBicicleta() != null ? data.getValue().getTheBicicleta().getSerial() : ""));

        colMecanicoOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getListMecanicos() != null && !data.getValue().getListMecanicos().isEmpty() ? data.getValue().getListMecanicos().get(0).getNombre() : ""));

        colMotivoOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivoServicio()));

        colEstadoOrden.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstado() != null ? data.getValue().getEstado().toString() : ""));

        cargarOrdenes();
    }

    private void cargarOrdenes() {
        tablaOrdenes.getItems().setAll(ordenController.getTaller().getListOrdenes().stream().filter(orden -> orden != null).toList());
    }

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

    @FXML
    private TableView<Orden> tablaOrdenes;

    @FXML
    private TableColumn<Orden, String> colCodigoOrden;

    @FXML
    private TableColumn<Orden, String> colFechaOrden;

    @FXML
    private TableColumn<Orden, String> colHoraOrden;

    @FXML
    private TableColumn<Orden, String> colBicicletaOrden;

    @FXML
    private TableColumn<Orden, String> colMecanicoOrden;

    @FXML
    private TableColumn<Orden, String> colMotivoOrden;

    @FXML
    private TableColumn<Orden, String> colEstadoOrden;

    // Botones
    @FXML
    private void agregarTarea() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/tarea.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        TareaViewController controller = fxmlLoader.getController();

        Stage ventana = new Stage();
        ventana.setTitle("Agregar tarea");
        ventana.setScene(scene);

        ventana.showAndWait();

        if (controller.getNombre() != null) {

            Tarea nuevaTarea = new Tarea(
                    controller.getNombre(),
                    controller.getDescripcion(),
                    controller.getCosto()
            );
            tareas.add(nuevaTarea);
        }
    }

    private void editarTarea(Tarea tarea) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/tarea.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        TareaViewController controller = fxmlLoader.getController();

        controller.prepararEdicion(tarea);

        Stage ventana = new Stage();
        ventana.setTitle("Editar tarea");
        ventana.setScene(scene);
        ventana.showAndWait();

        tablaTareas.refresh();
    }

    @FXML
    private void eliminarTarea() {
        Tarea tareaSeleccionada = tablaTareas.getSelectionModel().getSelectedItem();

        if (tareaSeleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Tarea no seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una tarea de la tabla para eliminarla.");
            alerta.showAndWait();
            return;
        }

        tareas.remove(tareaSeleccionada);
    }

    @FXML
    private void agregarRepuesto() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/repuesto.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        RepuestoViewController controller = fxmlLoader.getController();

        Stage ventana = new Stage();
        ventana.setTitle("Agregar repuesto");
        ventana.setScene(scene);

        ventana.showAndWait();

        if (controller.getNombre() != null) {

            Repuesto nuevoRepuesto = new Repuesto(
                    controller.getNombre(),
                    controller.getCantidad(),
                    controller.getCosto(),
                    new ArrayList<>()
            );
            repuestos.add(nuevoRepuesto);
        }
    }

    private void editarRepuesto(Repuesto repuesto) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/repuesto.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        RepuestoViewController controller = fxmlLoader.getController();

        controller.prepararEdicion(repuesto);

        Stage ventana = new Stage();
        ventana.setTitle("Editar repuesto");
        ventana.setScene(scene);

        ventana.showAndWait();

        tablaRepuestos.refresh();
    }

    @FXML
    private void eliminarRepuesto() {

        Repuesto repuestoSeleccionado = tablaRepuestos.getSelectionModel().getSelectedItem();

        if (repuestoSeleccionado == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Repuesto no seleccionado");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona un repuesto de la tabla para eliminarlo.");
            alerta.showAndWait();

            return;
        }

        repuestos.remove(repuestoSeleccionado);
    }

    @FXML
    private void editarOrden() {

        Orden seleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Orden no seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una orden de la tabla.");
            alerta.showAndWait();
            return;
        }

        ordenEditar = seleccionada;

        txtCodigo.setText(seleccionada.getCodigo());
        dpFecha.setValue(seleccionada.getFechaIngreso());
        txtHora.setText(seleccionada.getHoraIngreso().toString());
        txtMotivo.setText(seleccionada.getMotivoServicio());
        txtDiagnostico.setText(seleccionada.getDiagnostico());

        if (seleccionada.getTheBicicleta() != null) {
            cmbBicicleta.setValue(seleccionada.getTheBicicleta().getSerial());
        }

        if (seleccionada.getListMecanicos() != null &&
                !seleccionada.getListMecanicos().isEmpty()) {
            cmbMecanico.setValue(seleccionada.getListMecanicos().get(0).getCodigo());
        }

        tareas.clear();
        tareas.addAll(seleccionada.getListTareas());

        repuestos.clear();
        repuestos.addAll(seleccionada.getListRepuestos());

        tablaTareas.refresh();
        tablaRepuestos.refresh();
    }

    @FXML
    private void eliminarOrden() {

        Orden seleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Orden no seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una orden de la tabla.");
            alerta.showAndWait();
            return;
        }

        boolean eliminado = ordenController.eliminarOrden(seleccionada.getCodigo());

        if (eliminado) {
            tablaOrdenes.getItems().remove(seleccionada);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Orden eliminada");
            alerta.setHeaderText(null);
            alerta.setContentText("La orden se eliminó correctamente.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void guardarOrden() {

        String codigo = txtCodigo.getText();
        String motivo = txtMotivo.getText();
        String diagnostico = txtDiagnostico.getText();
        String horaTexto = txtHora.getText();

        if (codigo.isBlank()
                || dpFecha.getValue() == null
                || horaTexto.isBlank()
                || motivo.isBlank()
                || diagnostico.isBlank()
                || cmbBicicleta.getValue() == null
                || cmbMecanico.getValue() == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa todos los campos.");
            alerta.showAndWait();
            return;
        }

        LocalTime hora;

        try {
            hora = LocalTime.parse(horaTexto);
        } catch (DateTimeParseException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Hora inválida");
            alerta.setHeaderText(null);
            alerta.setContentText("La hora debe tener el formato HH:mm. Ejemplo: 14:30");
            alerta.showAndWait();
            return;
        }

        if (ordenEditar != null) {

            boolean actualizado = ordenController.actualizarOrden(
                    ordenEditar.getCodigo(),
                    motivo,
                    diagnostico,
                    ordenEditar.getEstado()
            );

            if (actualizado) {

                ordenEditar.getListTareas().clear();
                ordenEditar.getListTareas().addAll(tareas);

                ordenEditar.getListRepuestos().clear();
                ordenEditar.getListRepuestos().addAll(repuestos);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Orden actualizada");
                alerta.setHeaderText(null);
                alerta.setContentText("La orden se actualizó correctamente.");
                alerta.showAndWait();

                ordenEditar = null;

                txtCodigo.clear();
                dpFecha.setValue(null);
                txtHora.clear();
                txtMotivo.clear();
                txtDiagnostico.clear();
                cmbBicicleta.setValue(null);
                cmbMecanico.setValue(null);
                tareas.clear();
                repuestos.clear();

                cargarOrdenes();
            }

            return;
        }

        boolean registrada = ordenController.crearOrdenDeServicio(
                codigo,
                dpFecha.getValue(),
                hora,
                motivo,
                diagnostico,
                cmbBicicleta.getValue(),
                cmbMecanico.getValue());

        if (registrada) {

            for (Tarea tarea : tareas) {
                ordenController.agregarTareaAOrden(codigo, tarea.getNombre(), tarea.getDescripcion(), tarea.getCosto());
            }

            for (Repuesto repuesto : repuestos) {
                ordenController.agregarRepuestoAOrden(codigo, repuesto.getNombre(), repuesto.getCantidad(), repuesto.getCosto());
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Orden registrada");
            alerta.setHeaderText(null);
            alerta.setContentText("La orden se registró correctamente.");
            alerta.showAndWait();

            txtCodigo.clear();
            dpFecha.setValue(null);
            txtHora.clear();
            txtMotivo.clear();
            txtDiagnostico.clear();
            cmbBicicleta.setValue(null);
            cmbMecanico.setValue(null);
            tareas.clear();
            repuestos.clear();

            cargarOrdenes();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "No se pudo registrar la orden. " +
                            "Verifica que la bicicleta y el mecánico existan " +
                            "y que el código de la orden no esté registrado.");
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