package org.example.Model;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private String id;
    private String telefono;
    private String direccion;
    private ArrayList<Bicicleta> listBicicletas;
    private  ArrayList<Orden> listOrdenes;

    //Contructor


    public Cliente(String nombre, String id, String telefono, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente(String nombre, String id, String telefono, String direccion,
                   ArrayList<Bicicleta> listBicicletas, ArrayList<Orden> listOrdenes) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.listBicicletas = new ArrayList<>();
        this.listOrdenes = new ArrayList<>();
    }





    //Getter & Setter


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Bicicleta> getListBicicletas() {
        return listBicicletas;
    }

    public void setListBicicletas(ArrayList<Bicicleta> listBicicletas) {
        this.listBicicletas = listBicicletas;
    }

    public ArrayList<Orden> getListOrdenes() {
        return listOrdenes;
    }

    public void setListOrdenes(ArrayList<Orden> listOrdenes) {
        this.listOrdenes = listOrdenes;
    }
}
