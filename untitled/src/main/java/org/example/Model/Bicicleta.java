package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bicicleta implements IHistoriable{
    private String marca;
    private String color;
    private  String serial;
    private LocalDate antiguedad;
    private TipoBicicleta tipoBicicleta;
    private Cliente theCliente;
    private Orden theOrden;

    //Constructor

    public Bicicleta(String marca, String color, String serial, LocalDate antiguedad, TipoBicicleta tipoBicicleta, Cliente theCliente, Orden theOrden) {
        this.marca = marca;
        this.color = color;
        this.serial = serial;
        this.antiguedad = antiguedad;
        this.tipoBicicleta = tipoBicicleta;
        this.theCliente = theCliente;
        this.theOrden = theOrden;
    }

    public Bicicleta(String marca, String color, String serial, LocalDate antiguedad, TipoBicicleta tipoBicicleta) {
        this.marca = marca;
        this.color = color;
        this.serial = serial;
        this.antiguedad = antiguedad;
        this.tipoBicicleta = tipoBicicleta;
    }



    @Override
    public ArrayList<Object> regsitrarHistorial(String codigo) {
        return null;
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

    public LocalDate getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(LocalDate antiguedad) {
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
