package org.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Taller(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listClientes = new ArrayList<>();
        this.listBicicletas = new ArrayList<>();
        this.listOrdenes = new ArrayList<>();
        this.listMecanicos = new ArrayList<>();
        this.listRepuestos = new ArrayList<>();
    }


    //Adds
    public void addCliente(Cliente cliente) {
        listClientes.add(cliente);
    }

    public void addBicicleta(Bicicleta bicicleta) {
        listBicicletas.add(bicicleta);
    }

    public void addOrden(Orden orden) {
        listOrdenes.add(orden);
    }

    public void addMecanico(Mecanico mecanico) {
        listMecanicos.add(mecanico);
    }

    public void addRepuesto(Repuesto repuesto) {
        listRepuestos.add(repuesto);
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
            listMecanicos.get(posicion).setDisponible(disponible);
            return true;
        }
        return false;
    }


//Create tarea
public boolean crearTarea(String  nombre, String descripcion, int costo){
        Tarea tarea = new Tarea(nombre, descripcion, costo);
        return true;
}

//CRUD OrdenesServicio
// create
public boolean crearOrdenDeServicio(String codigo, LocalDate fecha, LocalTime horaIngreso, String motivo, String diagnostico, String serialBici, String cedulaMecanico) {
    int posBici = buscarBicicletaByCodigo(serialBici);
    int posMeca = buscarMecanicoByCedula(cedulaMecanico);

    if (posBici == -1 || posMeca == -1) {
        return false;
    }
    if (buscarOrdenByCodigo(codigo) != -1) {
        return false;
    }
    Bicicleta bici = listBicicletas.get(posBici);
    Mecanico meca = listMecanicos.get(posMeca);
    ArrayList<Mecanico> mecas = new ArrayList<>();
    mecas.add(meca);

    Orden nuevaOrden = new Orden(codigo, fecha, horaIngreso, motivo, diagnostico, bici, new ArrayList<>(), EstadoOrden.RECIBIDO, mecas, new ArrayList<>());

    bici.setTheOrden(nuevaOrden);
    bici.agregarAlHistorial(nuevaOrden);

    meca.getListOrdenes().add(nuevaOrden);
    listOrdenes.add(nuevaOrden);

    return true;
}
//read
public int buscarOrdenByCodigo(String codigo) {
    for (int i = 0; i < listOrdenes.size(); i++) {
        if (listOrdenes.get(i).getCodigo().equals(codigo)) {
            return i;
        }
    }
    return -1;
}

//update
public boolean actualizarOrden(String codigo, String motivoServicio, String diagnostico, EstadoOrden estado) {
    int posicion = buscarOrdenByCodigo(codigo);
    if (posicion != -1) {
        listOrdenes.get(posicion).setMotivoServicio(motivoServicio);
        listOrdenes.get(posicion).setDiagnostico(diagnostico);
        listOrdenes.get(posicion).setEstado(estado);
        return true;
    }
    return false;
}
//delete
public boolean eliminarOrden(String codigo) {
    int posicion = buscarOrdenByCodigo(codigo);
    if (posicion != -1) {
        listOrdenes.remove(posicion);
        return true;
    }
    return false;
}
//crud tarea en funcion a la orden
public boolean agregarTareaAOrden(String codigoOrden, String nombre, String descripcion, int costo) {
    int posOrden = buscarOrdenByCodigo(codigoOrden);
    if (posOrden == -1) {
        return false;
    }
    Orden orden = listOrdenes.get(posOrden);
    Tarea nuevaTarea = new Tarea(nombre, descripcion, costo);

    orden.getListTareas().add(nuevaTarea);
    orden.calcularCostoTotal();
    return true;
}
    public String mostrarTareasDeOrden(String codigoOrden) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return "Orden no encontrada";
        }
        Orden orden = listOrdenes.get(pos);
        if (orden.getListTareas().isEmpty()) {
            return "No hay tareas en esta orden";
        }
        String lista = "";
        for (Tarea t : orden.getListTareas()) {
            lista += "Tarea: " + t.getNombre() + "  Costo: " + t.getCosto() + "\n";
        }
        return lista; }

    public boolean actualizarTareaEnOrden(String codigoOrden, String nombreAntiguo, String nuevoNombre, String nuevaDescripcion, int nuevoCosto) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return false;
        }
        Orden orden = listOrdenes.get(pos);
        for (Tarea t : orden.getListTareas()) {
            if (t.getNombre().equals(nombreAntiguo)) {
                t.setNombre(nuevoNombre);
                t.setDescripcion(nuevaDescripcion);
                t.setCosto(nuevoCosto);
                orden.calcularCostoTotal();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarTareaDeOrden(String codigoOrden, String nombreTarea) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return false;
        }
        Orden orden = listOrdenes.get(pos);
        for (int i = 0; i < orden.getListTareas().size(); i++) {
            if (orden.getListTareas().get(i).getNombre().equals(nombreTarea)) {
                orden.getListTareas().remove(i);
                orden.calcularCostoTotal();
                return true;
            }
        }
        return false;
    }

//CRUD repuesto en funcion a la orden
    public boolean agregarRepuestoAOrden(String codigoOrden, String nombre, int cantidad, int costo) {
        int posOrden = buscarOrdenByCodigo(codigoOrden);
        if (posOrden == -1) {
            return false;
        }

        Orden orden = listOrdenes.get(posOrden);
        ArrayList<Orden> ordenAsociada = new ArrayList<>();
        ordenAsociada.add(orden);

        Repuesto nuevoRepuesto = new Repuesto(nombre, cantidad, costo, ordenAsociada);
        listRepuestos.add(nuevoRepuesto);

        orden.getListRepuestos().add(nuevoRepuesto);
        orden.calcularCostoTotal();
        return true;
    }

    public String mostrarRepuestosDeOrden(String codigoOrden) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return "Orden no encontrada";
        }
        Orden orden = listOrdenes.get(pos);
        if (orden.getListRepuestos().isEmpty()) {
            return "No hay repuestos en esta orden";
        }
        String lista = "";
        for (Repuesto r : orden.getListRepuestos()) {
            lista += "Repuesto: " + r.getNombre() + "  Costo: " + r.getCosto() + "\n";
        }
        return lista;
    }
    public boolean actualizarRepuestoEnOrden(String codigoOrden, String nombreAntiguo, String nuevoNombre, int nuevaCantidad, int nuevoCosto) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return false;
        }
        Orden orden = listOrdenes.get(pos);
        for (Repuesto r : orden.getListRepuestos()) {
            if (r.getNombre().equals(nombreAntiguo)) {
                r.setNombre(nuevoNombre);
                r.setCantidad(nuevaCantidad);
                r.setCosto(nuevoCosto);
                orden.calcularCostoTotal();
                return true;
            }
        }
        return false;
    }
    public boolean eliminarRepuestoDeOrden(String codigoOrden, String nombreRepuesto) {
        int pos = buscarOrdenByCodigo(codigoOrden);
        if (pos == -1) {
            return false;
        }
        Orden orden = listOrdenes.get(pos);
        for (int i = 0; i < orden.getListRepuestos().size(); i++) {
            if (orden.getListRepuestos().get(i).getNombre().equals(nombreRepuesto)) {
                orden.getListRepuestos().remove(i);
                orden.calcularCostoTotal();
                return true;
            }
        }
        return false;
    }

//las otras funcionalidades requeridaas
public ArrayList<Orden> verHistorialBicicleta(String serial) {
    int pos = buscarBicicletaByCodigo(serial);
    if (pos != -1) {
        return listBicicletas.get(pos).getHistorial();
    }
    return new ArrayList<>();
}

    public ArrayList<Orden> consultarOrdenesPorDia(LocalDate fecha) {
        ArrayList<Orden> filtradas = new ArrayList<>();
        for (Orden o : listOrdenes) {
            if (o.getFechaIngreso().equals(fecha)) {
                filtradas.add(o);
            }
        }
        return filtradas;
    }



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
