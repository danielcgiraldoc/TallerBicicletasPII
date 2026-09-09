package org.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Orden {
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private String diagnostico;
    private Cliente theCliente;
    private Bicicleta theBicicleta;
    private ArrayList<Tarea> listTareas;
    private EstadoOrden estado;
    private ArrayList<Mecanico> listMecanicos;
    private ArrayList<Repuesto> listRepuestos;

    //Contructor

    public Orden(LocalDate fechaIngreso, LocalTime horaIngreso, String diagnostico, Cliente theCliente, Bicicleta theBicicleta, ArrayList<Tarea> listTareas, EstadoOrden estado, ArrayList<Mecanico> listMecanicos, ArrayList<Repuesto> listRepuestos) {
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.diagnostico = diagnostico;
        this.theCliente = theCliente;
        this.theBicicleta = theBicicleta;
        this.listTareas = listTareas;
        this.estado = estado;
        this.listMecanicos = new ArrayList<>();
        this.listRepuestos = new ArrayList<>();
    }



    //Gtt && Stt


    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Cliente getTheCliente() {
        return theCliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.theCliente = theCliente;
    }

    public Bicicleta getTheBicicleta() {
        return theBicicleta;
    }

    public void setTheBicicleta(Bicicleta theBicicleta) {
        this.theBicicleta = theBicicleta;
    }

    public ArrayList<Tarea> getListTareas() {
        return listTareas;
    }

    public void setListTareas(ArrayList<Tarea> listTareas) {
        this.listTareas = listTareas;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public ArrayList<Mecanico> getListMecanicos() {
        return listMecanicos;
    }

    public void setListMecanicos(ArrayList<Mecanico> listMecanicos) {
        this.listMecanicos = listMecanicos;
    }

    public ArrayList<Repuesto> getListRepuestos() {
        return listRepuestos;
    }

    public void setListRepuestos(ArrayList<Repuesto> listRepuestos) {
        this.listRepuestos = listRepuestos;
    }
}
