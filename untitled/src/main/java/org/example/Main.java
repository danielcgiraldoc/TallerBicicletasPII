package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main extends Application {

    public static Taller taller;
    public static Taller getTaller() {
        return taller;
    }

    @Override
    public void start(Stage stage) throws Exception {

        taller = new Taller("12345", "Taller de Bicicletas", "Armenia, Quindio");

        // CLIENTES

        Cliente cliente1 = new Cliente("Daniel Garcia", "1001", "3101234567", "Cra 15 #20-10");

        Cliente cliente2 = new Cliente("Laura Martinez", "1002", "3117654321", "Calle 12 #8-25");

        Cliente cliente3 = new Cliente("Juan Rodriguez", "1003", "3209876543", "Carrera 20 #15-30");

        taller.getListClientes().add(cliente1);
        taller.getListClientes().add(cliente2);
        taller.getListClientes().add(cliente3);

        // BICICLETAS

        Bicicleta bicicleta1 = new Bicicleta("GW", "Rojo", "BICI001", 2, TipoBicicleta.RUTA);

        Bicicleta bicicleta2 = new Bicicleta("Scott", "Negro", "BICI002", 4, TipoBicicleta.MTB);

        Bicicleta bicicleta3 = new Bicicleta("Trek", "Azul", "BICI003", 1, TipoBicicleta.ELÉCTRICA);

        bicicleta1.setTheCliente(cliente1);
        bicicleta2.setTheCliente(cliente2);
        bicicleta3.setTheCliente(cliente3);

        cliente1.getListBicicletas().add(bicicleta1);
        cliente2.getListBicicletas().add(bicicleta2);
        cliente3.getListBicicletas().add(bicicleta3);

        taller.getListBicicletas().add(bicicleta1);
        taller.getListBicicletas().add(bicicleta2);
        taller.getListBicicletas().add(bicicleta3);

        // MECÁNICOS

        Mecanico mecanico1 = new Mecanico("Andres Lopez", "M001", true);

        Mecanico mecanico2 = new Mecanico("Miguel Torres", "M002", true);

        Mecanico mecanico3 = new Mecanico("Sebastian Perez", "M003", true);

        mecanico1.setEspecialidad(Especialidad.FRENOS);
        mecanico2.setEspecialidad(Especialidad.TRANSMISION);
        mecanico3.setEspecialidad(Especialidad.B_ELECTRICAS);

        taller.getListMecanicos().add(mecanico1);
        taller.getListMecanicos().add(mecanico2);
        taller.getListMecanicos().add(mecanico3);

        // ORDEN 1 - BICICLETA 1

        ArrayList<Mecanico> mecanicosOrden1 = new ArrayList<>();
        mecanicosOrden1.add(mecanico1);

        Orden orden1 = new Orden("ORD001", LocalDate.of(2026, 9, 10), LocalTime.of(8, 30), "Requiere mantenimiento preventivo", "Mantenimiento general", bicicleta1, new ArrayList<>(), EstadoOrden.RECIBIDO, mecanicosOrden1, new ArrayList<>());

        Tarea tarea1 = new Tarea("Limpieza general", "Limpieza completa de la bicicleta", 30000);

        Tarea tarea2 = new Tarea("Lubricacion", "Lubricacion de cadena y componentes", 20000);

        Repuesto repuesto1 = new Repuesto("Lubricante", 1, 15000, new ArrayList<>());

        Repuesto repuesto2 = new Repuesto("Cable de freno", 2, 10000, new ArrayList<>());

        orden1.getListTareas().add(tarea1);
        orden1.getListTareas().add(tarea2);

        orden1.getListRepuestos().add(repuesto1);
        orden1.getListRepuestos().add(repuesto2);

        bicicleta1.setTheOrden(orden1);
        bicicleta1.agregarAlHistorial(orden1);
        mecanico1.getListOrdenes().add(orden1);

        taller.getListOrdenes().add(orden1);

        // ORDEN 2 - BICICLETA 1

        ArrayList<Mecanico> mecanicosOrden2 = new ArrayList<>();
        mecanicosOrden2.add(mecanico2);

        Orden orden2 = new Orden("ORD002", LocalDate.of(2026, 9, 11), LocalTime.of(10, 0), "Pastillas de freno desgastadas", "Cambio de frenos", bicicleta1, new ArrayList<>(), EstadoOrden.EN_PROCESO, mecanicosOrden2, new ArrayList<>());

        Tarea tarea3 = new Tarea("Ajuste de frenos", "Ajuste y calibracion de frenos", 25000);

        Tarea tarea4 = new Tarea("Revision de pastillas", "Revision del sistema de frenado", 15000);

        Repuesto repuesto3 = new Repuesto("Pastillas de freno", 2, 20000, new ArrayList<>());

        Repuesto repuesto4 = new Repuesto("Cable de freno", 1, 12000, new ArrayList<>());

        orden2.getListTareas().add(tarea3);
        orden2.getListTareas().add(tarea4);

        orden2.getListRepuestos().add(repuesto3);
        orden2.getListRepuestos().add(repuesto4);

        bicicleta1.setTheOrden(orden2);
        bicicleta1.agregarAlHistorial(orden2);
        mecanico2.getListOrdenes().add(orden2);

        taller.getListOrdenes().add(orden2);

        // ORDEN 3 - BICICLETA 1

        ArrayList<Mecanico> mecanicosOrden3 = new ArrayList<>();
        mecanicosOrden3.add(mecanico3);

        Orden orden3 = new Orden("ORD003", LocalDate.of(2026, 9, 12), LocalTime.of(14, 30), "Los cambios presentan dificultad", "Ajuste de cambios", bicicleta1, new ArrayList<>(), EstadoOrden.FINALIZADO, mecanicosOrden3, new ArrayList<>());

        Tarea tarea5 = new Tarea("Ajuste de cambios", "Calibracion del sistema de cambios", 30000);

        Tarea tarea6 = new Tarea("Revision de desviador", "Revision y ajuste del desviador", 20000);

        Repuesto repuesto5 = new Repuesto("Cable de cambio", 1, 12000, new ArrayList<>());

        Repuesto repuesto6 = new Repuesto("Guaya de cambio", 1, 15000, new ArrayList<>());

        orden3.getListTareas().add(tarea5);
        orden3.getListTareas().add(tarea6);

        orden3.getListRepuestos().add(repuesto5);
        orden3.getListRepuestos().add(repuesto6);

        bicicleta1.setTheOrden(orden3);
        bicicleta1.agregarAlHistorial(orden3);
        mecanico3.getListOrdenes().add(orden3);

        taller.getListOrdenes().add(orden3);

        // ORDEN 4 - BICICLETA 2

        ArrayList<Mecanico> mecanicosOrden4 = new ArrayList<>();
        mecanicosOrden4.add(mecanico2);

        Orden orden4 = new Orden("ORD004", LocalDate.of(2026, 9, 10), LocalTime.of(9, 0), "La transmision presenta ruido", "Revision de transmision", bicicleta2, new ArrayList<>(), EstadoOrden.RECIBIDO, mecanicosOrden4, new ArrayList<>());

        Tarea tarea7 = new Tarea("Revision de transmision", "Revision completa de transmision", 35000);

        Tarea tarea8 = new Tarea("Ajuste de piñones", "Ajuste del sistema de piñones", 25000);

        Repuesto repuesto7 = new Repuesto("Piñon", 1, 45000, new ArrayList<>());

        Repuesto repuesto8 = new Repuesto("Cadena", 1, 50000, new ArrayList<>());

        orden4.getListTareas().add(tarea7);
        orden4.getListTareas().add(tarea8);

        orden4.getListRepuestos().add(repuesto7);
        orden4.getListRepuestos().add(repuesto8);

        bicicleta2.setTheOrden(orden4);
        bicicleta2.agregarAlHistorial(orden4);
        mecanico2.getListOrdenes().add(orden4);

        taller.getListOrdenes().add(orden4);

        // ORDEN 5 - BICICLETA 2

        ArrayList<Mecanico> mecanicosOrden5 = new ArrayList<>();
        mecanicosOrden5.add(mecanico3);

        Orden orden5 = new Orden("ORD005", LocalDate.of(2026, 9, 11), LocalTime.of(11, 30), "Cadena desgastada y con perdida de tension", "Cambio de cadena", bicicleta2, new ArrayList<>(), EstadoOrden.EN_PROCESO, mecanicosOrden5, new ArrayList<>());

        Tarea tarea9 = new Tarea("Instalacion de cadena", "Instalacion y ajuste de cadena nueva", 25000);

        Tarea tarea10 = new Tarea("Ajuste de transmision", "Ajuste de los componentes de transmision", 20000);

        Repuesto repuesto9 = new Repuesto("Cadena", 1, 50000, new ArrayList<>());

        Repuesto repuesto10 = new Repuesto("Eslabon rapido", 1, 8000, new ArrayList<>());

        orden5.getListTareas().add(tarea9);
        orden5.getListTareas().add(tarea10);

        orden5.getListRepuestos().add(repuesto9);
        orden5.getListRepuestos().add(repuesto10);

        bicicleta2.setTheOrden(orden5);
        bicicleta2.agregarAlHistorial(orden5);
        mecanico3.getListOrdenes().add(orden5);

        taller.getListOrdenes().add(orden5);

        // ORDEN 6 - BICICLETA 2

        ArrayList<Mecanico> mecanicosOrden6 = new ArrayList<>();
        mecanicosOrden6.add(mecanico1);

        Orden orden6 = new Orden("ORD006", LocalDate.of(2026, 9, 13), LocalTime.of(15, 0), "Suspension delantera requiere mantenimiento", "Mantenimiento de suspension", bicicleta2, new ArrayList<>(), EstadoOrden.FINALIZADO, mecanicosOrden6, new ArrayList<>());

        Tarea tarea11 = new Tarea("Mantenimiento suspension", "Limpieza y ajuste de suspension", 50000);

        Tarea tarea12 = new Tarea("Revision horquilla", "Revision del estado de la horquilla", 30000);

        Repuesto repuesto11 = new Repuesto("Kit suspension", 1, 80000, new ArrayList<>());

        Repuesto repuesto12 = new Repuesto("Aceite suspension", 1, 25000, new ArrayList<>());

        orden6.getListTareas().add(tarea11);
        orden6.getListTareas().add(tarea12);

        orden6.getListRepuestos().add(repuesto11);
        orden6.getListRepuestos().add(repuesto12);

        bicicleta2.setTheOrden(orden6);
        bicicleta2.agregarAlHistorial(orden6);
        mecanico1.getListOrdenes().add(orden6);

        taller.getListOrdenes().add(orden6);

        // ORDEN 7 - BICICLETA 3

        ArrayList<Mecanico> mecanicosOrden7 = new ArrayList<>();
        mecanicosOrden7.add(mecanico3);

        Orden orden7 = new Orden("ORD007", LocalDate.of(2026, 9, 10), LocalTime.of(10, 30), "Se requiere revisar el sistema electrico", "Revision electrica", bicicleta3, new ArrayList<>(), EstadoOrden.RECIBIDO, mecanicosOrden7, new ArrayList<>());

        Tarea tarea13 = new Tarea("Diagnostico electrico", "Revision del sistema electrico", 40000);

        Tarea tarea14 = new Tarea("Revision de motor", "Revision del funcionamiento del motor", 35000);

        Repuesto repuesto13 = new Repuesto("Cable electrico", 2, 10000, new ArrayList<>());

        Repuesto repuesto14 = new Repuesto("Conector electrico", 2, 7000, new ArrayList<>());

        orden7.getListTareas().add(tarea13);
        orden7.getListTareas().add(tarea14);

        orden7.getListRepuestos().add(repuesto13);
        orden7.getListRepuestos().add(repuesto14);

        bicicleta3.setTheOrden(orden7);
        bicicleta3.agregarAlHistorial(orden7);
        mecanico3.getListOrdenes().add(orden7);

        taller.getListOrdenes().add(orden7);

        // ORDEN 8 - BICICLETA 3

        ArrayList<Mecanico> mecanicosOrden8 = new ArrayList<>();
        mecanicosOrden8.add(mecanico1);

        Orden orden8 = new Orden("ORD008", LocalDate.of(2026, 9, 12), LocalTime.of(13, 0), "Bateria presenta bajo rendimiento", "Cambio de bateria", bicicleta3, new ArrayList<>(), EstadoOrden.EN_PROCESO, mecanicosOrden8, new ArrayList<>());

        Tarea tarea15 = new Tarea("Instalacion bateria", "Instalacion y configuracion de bateria", 35000);

        Tarea tarea16 = new Tarea("Revision de conexiones", "Revision de conexiones electricas", 25000);

        Repuesto repuesto15 = new Repuesto("Bateria", 1, 250000, new ArrayList<>());

        Repuesto repuesto16 = new Repuesto("Conector bateria", 1, 15000, new ArrayList<>());

        orden8.getListTareas().add(tarea15);
        orden8.getListTareas().add(tarea16);

        orden8.getListRepuestos().add(repuesto15);
        orden8.getListRepuestos().add(repuesto16);

        bicicleta3.setTheOrden(orden8);
        bicicleta3.agregarAlHistorial(orden8);
        mecanico1.getListOrdenes().add(orden8);

        taller.getListOrdenes().add(orden8);

        // ORDEN 9 - BICICLETA 3

        ArrayList<Mecanico> mecanicosOrden9 = new ArrayList<>();
        mecanicosOrden9.add(mecanico2);

        Orden orden9 = new Orden("ORD009", LocalDate.of(2026, 9, 14), LocalTime.of(16, 0), "Se requiere ajuste general de componentes", "Ajuste general", bicicleta3, new ArrayList<>(), EstadoOrden.FINALIZADO, mecanicosOrden9, new ArrayList<>());

        Tarea tarea17 = new Tarea("Ajuste general", "Ajuste general de componentes", 30000);

        Tarea tarea18 = new Tarea("Revision final", "Revision general de la bicicleta", 20000);

        Repuesto repuesto17 = new Repuesto("Tornillos", 4, 3000, new ArrayList<>());

        Repuesto repuesto18 = new Repuesto("Arandelas", 4, 2000, new ArrayList<>());

        orden9.getListTareas().add(tarea17);
        orden9.getListTareas().add(tarea18);

        orden9.getListRepuestos().add(repuesto17);
        orden9.getListRepuestos().add(repuesto18);

        bicicleta3.setTheOrden(orden9);
        bicicleta3.agregarAlHistorial(orden9);
        mecanico2.getListOrdenes().add(orden9);

        taller.getListOrdenes().add(orden9);

        // INICIAR INTERFAZ

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Taller de Bicicletas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}