package org.example.controller;

import org.example.Model.Cliente;
import org.example.Model.Taller;

public class ClienteController {

    private Taller taller;

    public ClienteController(Taller taller) {
        this.taller = taller;
    }

    public Taller getTaller() {
        return taller;
    }

    public boolean registrarCliente(String nombre, String id, String telefono, String direccion) {
        return taller.registrarCliente(nombre, id, telefono, direccion);
    }

    public Cliente buscarCliente(String id) {
        return taller.mostrarCliente(id);
    }

    public boolean actualizarCliente(String nombre, String id, String telefono) {
        return taller.actualizarCliente(nombre, id, telefono);
    }

    public boolean eliminarCliente(String id) {
        return taller.eliminarCliente(id);
    }
}
