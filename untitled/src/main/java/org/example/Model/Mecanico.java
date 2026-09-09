package org.example.Model;

import java.util.ArrayList;

public class Mecanico implements IDisponible{

   private String nombre;
    private String codigo;
    private boolean disponible;
    private  ArrayList<Orden> listOrdenes;
    private Especialidad especialidad;


    //Contructor

    public Mecanico(String nombre, String codigo, boolean disponible,  ArrayList<Orden> listOrdenes, Especialidad especialidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.disponible = disponible;
        this.listOrdenes = new ArrayList<>();
        this.especialidad = especialidad;
    }
    public Mecanico(String nombre, String codigo, boolean disponible) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.disponible = disponible;

    }


    @Override
    public boolean estaDisponible() {
        return disponible;
    }
    @Override
    public void actualizarDisponibilidad() {

        for (Orden o : listOrdenes) {
            if (o.getEstado() == EstadoOrden.EN_PROCESO) {
                disponible = false;
            } else {
                disponible = true;
            }
        }

    }




    //Gtt && Stt

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Orden> getListOrdenes() {
        return listOrdenes;
    }

    public void setListOrdenes(ArrayList<Orden> listOrdenes) {
        this.listOrdenes = listOrdenes;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
