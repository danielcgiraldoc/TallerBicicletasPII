package org.example.controller;

import org.example.Model.Mecanico;
import org.example.Model.Taller;

public class MecanicoController {

    private Taller taller;

    public MecanicoController(Taller taller) {
        this.taller = taller;
    }

    public Taller getTaller() {
        return taller;
    }

    public boolean registrarMecanico(String nombre, String codigo, boolean disponible) {
        return taller.registrarMecanico(nombre, codigo, disponible);
    }

    public Mecanico buscarMecanico(String codigo) {
        return taller.mostrarMecanico(codigo);
    }

    public String mostrarListaMecanicos() {
        return taller.mostrarListMecanico();
    }

    public boolean actualizarMecanico(String nombre, String codigo, boolean disponible) {
        return taller.actualizarMecanico(nombre, codigo, disponible);
    }

    public boolean eliminarMecanico(String codigo) {
        return taller.eliminarMecanico(codigo);
    }
}