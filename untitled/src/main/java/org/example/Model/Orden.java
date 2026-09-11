package org.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Orden {
    private String codigo;
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private String diagnostico;
    private Bicicleta theBicicleta;
    private String motivoServicio;
    private ArrayList<Tarea> listTareas;
    private EstadoOrden estado;
    private ArrayList<Mecanico> listMecanicos;
    private ArrayList<Repuesto> listRepuestos;

    @Override
    public String toString() {
        return "Orden{" +
                "codigo='" + codigo + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", horaIngreso=" + horaIngreso +
                ", diagnostico='" + diagnostico + '\'' +
                ", motivoServicio='" + motivoServicio + '\'' +
                ", cliente=" + (theBicicleta.getTheCliente().getNombre() +
                ", bicicleta=" + (theBicicleta != null ? theBicicleta.getMarca() : "null") +
                ", tareas=" + listTareas.size() +
                ", estado=" + estado +
                ", mecanicos=" + listMecanicos.size() +
                ", repuestos=" + listRepuestos.size());
    }


    //Contructor

    public Orden(String codigo, LocalDate fechaIngreso, LocalTime horaIngreso, String diagnostico, String motivoServicio, Bicicleta theBicicleta, ArrayList<Tarea> listTareas, EstadoOrden estado, ArrayList<Mecanico> listMecanicos, ArrayList<Repuesto> listRepuestos) {
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.diagnostico = diagnostico;
        this.theBicicleta = theBicicleta;
        this.listTareas = listTareas;
        this.estado = estado;
        this.motivoServicio = motivoServicio;
        this.listMecanicos = new ArrayList<>();
        this.listRepuestos = new ArrayList<>();
    }

    int calcularCostoTotal() {
        int total = 0;
        for (Repuesto r : listRepuestos) {
            total += r.getCosto();
        }
        for (Tarea t : listTareas) {
            total += t.getCosto();
        }
        return total;
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

    public String getMotivoServicio() {
        return motivoServicio;
    }

    public void setMotivoServicio(String motivoServicio) {
        this.motivoServicio = motivoServicio;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
