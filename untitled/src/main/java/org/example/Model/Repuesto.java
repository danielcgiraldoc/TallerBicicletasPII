package org.example.Model;

import java.util.ArrayList;

public class Repuesto implements IDisponible {
    private String nombre;
    private int cantidad;
    private int costo;
    private ArrayList<Orden> listOrdenes;

    //Constructor
    public Repuesto(String nombre, int cantidad, int costo, ArrayList<Orden> listOrdenes) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
        this.listOrdenes = new ArrayList<>();
    }

    @Override
    public boolean verDisponibilidad(String codigo) {
        return false;
    }

  //Gtt & Stt

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public ArrayList<Orden> getListOrdenes() {
        return listOrdenes;
    }

    public void setListOrdenes(ArrayList<Orden> listOrdenes) {
        this.listOrdenes = listOrdenes;
    }
}
