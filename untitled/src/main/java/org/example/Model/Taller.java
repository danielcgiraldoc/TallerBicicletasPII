package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Taller {
    private String nit;
    private String  nombre;
    private String direccion;
    private ArrayList<Cliente> listClientes;
    private ArrayList<Bicicleta> listBicicletas;
    private ArrayList<Orden> listOrdenes;
    private ArrayList<Mecanico> listMecanicos;
    private ArrayList<Repuesto> listRepuestos;


    //Contructor

    public Taller(String nit, String nombre, String direccion, ArrayList<Cliente> listClientes, ArrayList<Bicicleta> listBicicletas, ArrayList<Orden> listOrdenes, ArrayList<Mecanico> listMecanicos, ArrayList<Repuesto> listRepuestos) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listClientes = new ArrayList<>();
        this.listBicicletas = new ArrayList<>();
        this.listOrdenes = new ArrayList<>();
        this.listMecanicos = new ArrayList<>();
        this.listRepuestos = new ArrayList<>();
    }


    //CRUD CLIENTE
    public boolean registrarCliente(String nombre, String id, String telefono, String direccion) {
        Cliente nuevoCliente = new Cliente(nombre, id, telefono, direccion);

        if (buscarClienteById(id) == -1) {
            listClientes.add(nuevoCliente);
            return true;
        }

        return false;
    }

    public int buscarClienteById(String idBuscar) {
        for (int i = 0; i < listClientes.size(); i++) {
            if (listClientes.get(i) != null && listClientes.get(i).getId().equals(idBuscar)) {
                return i;
            }
        }
        return -1;
    }

    //Read - Un Cliente
    public Cliente mostrarCliente(String idBuscar) {
        int posicion = buscarClienteById(idBuscar);
        return posicion != -1 ? listClientes.get(posicion) : null;
    }

    //Read - Toda la lista
    public String mostrarListaClientes() {
        String lista = "";
        for (int i = 0; i < listClientes.size(); i++) {
            if (listClientes.get(i) != null) {
                lista += "Nombre: " + listClientes.get(i).getNombre() +
                        " | Cedula: " + listClientes.get(i).getId() +
                        " | Telefono: " + listClientes.get(i).getTelefono() + "\n";

            }
        }
        return "No hay clientes registrados";
    }

    //Update
    public boolean actualizarCliente(String nombre, String idBuscar, String telefono) {
        int posicion = buscarClienteById(idBuscar);
        if (posicion != -1) {
            listClientes.get(posicion).setNombre(nombre);
            listClientes.get(posicion).setTelefono(telefono);
            return true;
        }
        return false;
    }

    //Delate
    public boolean eliminarCliente(String idBuscar) {
        int posicion = buscarClienteById(idBuscar);
        if (posicion != -1) {
            listClientes.set(posicion, null);
            return true;
        }
        return false;
    }

    //CRUD Bicicleta
    //Create
    public boolean registrarBicicleta(String marco, String color, String serial, LocalDate antiguedad, TipoBicicleta tipoBicicleta, Cliente clienteRelacionado) {
        int posicionCliente = buscarClienteById(clienteRelacionado.getId());
        if (posicionCliente == -1) {
            return false;
        }
        if (buscarBicicletaByCodigo(serial) != -1) {
            return false;
        }

        Cliente cliente = listClientes.get(posicionCliente);

        Bicicleta nuevaBicicleta = new Bicicleta(marco, color, serial, antiguedad, tipoBicicleta);
        nuevaBicicleta.setTheCliente(clienteRelacionado);
        cliente.getListBicicletas().add(nuevaBicicleta);
        listBicicletas.add(nuevaBicicleta);

        return true;
    }

    public int buscarBicicletaByCodigo(String serialBuscar) {
        for (int i = 0; i < listBicicletas.size(); i++) {
            if (listBicicletas.get(i) != null && listBicicletas.get(i).getSerial().equals(serialBuscar)) {
                return i;
            }
        }
        return -1;
    }

    //Read Bicicleta
    public Bicicleta mostrarBicicleta(String codigo) {
        int posicion = buscarBicicletaByCodigo(codigo);
        if (posicion != -1) {
            return listBicicletas.get(posicion);
        }
        return null;
    }

    //Read listBicicleta
    public String mostrarListaBicicleta() {
        String lista = "";
        for (Bicicleta b : listBicicletas){
            lista += "Marca: " + b.getMarca() +
                    " Color: " + b.getColor() +
                    " Serial: " + b.getSerial() +
                    " Antigüedad: " + b.getAntiguedad() +
                    " Tipo: " + b.getTipoBicicleta() + "\n";
        }
        return lista;
    }

    //Update
    public boolean actualizarBicicleta(String marca, String color, String serial, LocalDate antiguedad, TipoBicicleta tipoBicicleta) {
        int posicion = buscarBicicletaByCodigo(serial);
        if (posicion != -1) {
            listBicicletas.get(posicion).setMarca(marca);
            listBicicletas.get(posicion).setColor(color);
            listBicicletas.get(posicion).setSerial(serial);
            listBicicletas.get(posicion).setAntiguedad(antiguedad);
            listBicicletas.get(posicion).setTipoBicicleta(tipoBicicleta);
            return true;
        }
        return false;
    }

    //Delate
    public boolean elimarBicicleta(String serial) {
        int posicion = buscarBicicletaByCodigo(serial);
        if (posicion != -1) {
            listBicicletas.set(posicion, null);
            return true;
        }
        return false;
    }

    //CRUD Mecanico
    //Create
    public boolean registrarMecanico(String nombre, String codigo, boolean disponible) {
        Mecanico nuevoMecanico = new Mecanico(nombre, codigo, disponible);

        if (buscarMecanicoByCedula(codigo) == -1) {
            listMecanicos.add(nuevoMecanico);
            return true;
        }
        return false;
    }

    public int buscarMecanicoByCedula(String cedulaBuscar) {
        for (int i = 0; i < listMecanicos.size(); i++) {
            if (listMecanicos.get(i).getCodigo().equals(cedulaBuscar)) {
                return i;
            }
        }
        return -1;
    }

    //Read
    public Mecanico mostrarMecanico(String cedula) {
        int posicion = buscarMecanicoByCedula(cedula);
        if (posicion != -1) {
            return listMecanicos.get(posicion);
        }
        return null;
    }

    public String mostrarListMecanico() {
        String lista = "";
        for (Mecanico m : listMecanicos){
                lista += "Nombre: " + m.getNombre() +
                        " | Cedula: " + m.getCodigo()+
                        " Especialidad: " + m.getEspecialidad()
        +"\n";

            }
    return lista;
    }

    //Update
    public boolean actualizarMecanico(String nombre, String codigo, boolean disponible) {
        int posicion = buscarMecanicoByCedula(codigo);
        if (posicion != -1) {
            listMecanicos.get(posicion).setNombre(nombre);
            listMecanicos.get(posicion).setCodigo(codigo);
            listMecanicos.get(posicion).setDisponibilidad(disponible);
            return true;
        }
        return false;
    }


//Create tarea









    //Gett & Sett

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(ArrayList<Cliente> listClientes) {
        this.listClientes = listClientes;
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

    public ArrayList<Mecanico> getLsitMecanicos() {
        return lsitMecanicos;
    }

    public void setLsitMecanicos(ArrayList<Mecanico> lsitMecanicos) {
        this.lsitMecanicos = lsitMecanicos;
    }

    public ArrayList<Repuesto> getListRepuestos() {
        return listRepuestos;
    }

    public void setListRepuestos(ArrayList<Repuesto> listRepuestos) {
        this.listRepuestos = listRepuestos;
    }
}
