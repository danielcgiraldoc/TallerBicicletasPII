package org.example.controller;

import org.example.Model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrdenController {

    private Taller taller;

    public OrdenController(Taller taller) {
        this.taller = taller;
    }

    public Taller getTaller() {
        return taller;
    }

    public boolean crearOrdenDeServicio(String codigo, LocalDate fecha, LocalTime horaIngreso, String motivo, String diagnostico, String serialBici, String cedulaMecanico) {
        return taller.crearOrdenDeServicio(codigo, fecha, horaIngreso, motivo, diagnostico, serialBici, cedulaMecanico);
    }

    public Orden buscarOrden(String codigo) {
        return taller.mostrarOrden(codigo);
    }

    public boolean actualizarOrden(String codigo, String motivoServicio, String diagnostico, EstadoOrden estado) {

        return taller.actualizarOrden(codigo, motivoServicio, diagnostico, estado);
    }

    public boolean eliminarOrden(String codigo) {
        return taller.eliminarOrden(codigo);
    }

    public ArrayList<Bicicleta> obtenerBicicletas() {
        return taller.getListBicicletas();
    }

    public ArrayList<Mecanico> obtenerMecanicos() {
        return taller.getListMecanicos();
    }

    public boolean agregarTareaAOrden(String codigoOrden, String nombre, String descripcion, int costo) {

        return taller.agregarTareaAOrden(codigoOrden, nombre, descripcion, costo);
    }

    public boolean agregarRepuestoAOrden(String codigoOrden, String nombre, int cantidad, int costo) {

        return taller.agregarRepuestoAOrden(codigoOrden, nombre, cantidad, costo);
    }
}