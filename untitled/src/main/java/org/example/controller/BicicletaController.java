package org.example.controller;

import org.example.Model.Bicicleta;
import org.example.Model.Cliente;
import org.example.Model.Taller;
import org.example.Model.TipoBicicleta;

import java.time.LocalDate;

public class BicicletaController {

    private Taller taller;

    public BicicletaController(Taller taller) {
        this.taller = taller;
    }

    public Taller getTaller() {
        return taller;
    }

    public boolean registrarBicicleta(String marco, String color, String serial,
                                      int antiguedad, TipoBicicleta tipoBicicleta,
                                      String idCliente) {

        Cliente cliente = taller.mostrarCliente(idCliente);

        if (cliente == null) {
            return false;
        }

        return taller.registrarBicicleta(marco, color, serial, antiguedad, tipoBicicleta, cliente);
    }

    public Bicicleta buscarBicicleta(String serial) {
        return taller.mostrarBicicleta(serial);
    }

    public boolean actualizarBicicleta(String marca, String color, String serial,
                                       int antiguedad, TipoBicicleta tipoBicicleta) {
        return taller.actualizarBicicleta(marca, color, serial, antiguedad, tipoBicicleta);
    }

    public boolean elimarBicicleta(String serial) {
        return taller.elimarBicicleta(serial);
    }
}