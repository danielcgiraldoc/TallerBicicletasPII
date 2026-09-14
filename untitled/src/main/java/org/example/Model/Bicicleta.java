package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Bicicleta implements IHistoriable{
    private String marca;
    private String color;
    private String serial;
    private int antiguedad;
    private TipoBicicleta tipoBicicleta;
    private Cliente theCliente;
    private Orden theOrden;
    private ArrayList<Orden> historialServicios;

    //Constructor

    public Bicicleta(String marca, String color, String serial, int antiguedad, TipoBicicleta tipoBicicleta, Cliente theCliente, Orden theOrden){
        this.marca = marca;
        this.color = color;
        this.serial = serial;
        this.antiguedad = antiguedad;
        this.tipoBicicleta = tipoBicicleta;
        this.theCliente = theCliente;
        this.theOrden = theOrden;
        this.historialServicios = new ArrayList<>();
    }

    public Bicicleta(String marca, String color, String serial, int antiguedad, TipoBicicleta tipoBicicleta) {
        this.marca = marca;
        this.color = color;
        this.serial = serial;
        this.antiguedad = antiguedad;
        this.tipoBicicleta = tipoBicicleta;
        this.historialServicios = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", serial='" + serial + '\'' +
                ", antiguedad=" + antiguedad +
                ", historialServicios=" + historialServicios.stream()
                .map(Orden::getCodigo)
                .collect(Collectors.joining(", ")) +
                '}';
    }

    @Override
    public void agregarAlHistorial(Orden orden) {
    historialServicios.add(orden);
    }

    @Override
    public ArrayList<Orden> getHistorial() {
        return historialServicios;
    }





    //Getter & Setter

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public TipoBicicleta getTipoBicicleta() {
        return tipoBicicleta;
    }

    public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
        this.tipoBicicleta = tipoBicicleta;
    }

    public Cliente getTheCliente() {
        return theCliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.theCliente = theCliente;
    }

    public Orden getTheOrden() {
        return theOrden;
    }

    public void setTheOrden(Orden theOrden) {
        this.theOrden = theOrden;
    }



}
