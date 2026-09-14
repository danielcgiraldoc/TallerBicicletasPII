package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TallerTest {

    private Taller taller;

    @BeforeEach
    void setUp() {
        taller = new Taller("1", "Taller", "Dir");

        Cliente cliente = new Cliente("Luis", "1", "111", "Dir");
        taller.addCliente(cliente);
        taller.registrarBicicleta("GW", "Rojo", "1", 5, TipoBicicleta.MTB, cliente);
        taller.registrarMecanico("Pedro", "1", true);

        taller.crearOrdenDeServicio("1", LocalDate.now(), LocalTime.now(), "Rev", "Diag", "1", "1");
    }

    @Test
    void testRegistrarYBuscarCliente() {
        assertTrue(taller.registrarCliente("Ana", "2", "222", "Dir"));
        assertFalse(taller.registrarCliente("Ana 2", "2", "333", "Dir"));
        
        assertEquals(0, taller.buscarClienteById("1"));
        assertEquals(-1, taller.buscarClienteById("9"));
    }

    @Test
    void testActualizarCliente() {
        assertTrue(taller.actualizarCliente("Luis P", "1", "333"));
        assertEquals("Luis P", taller.mostrarCliente("1").getNombre());
    }

    @Test
    void testMostrarCliente() {
        assertNotNull(taller.mostrarCliente("1"));
        assertEquals("Luis", taller.mostrarCliente("1").getNombre());
        assertNull(taller.mostrarCliente("9"));
    }

    @Test
    void testMostrarListaClientes() {
        String lista = taller.mostrarListaClientes();
        assertTrue(lista.contains("Luis"));
        
        Taller tallerVacio = new Taller("2", "Taller 2", "Dir 2");
        assertEquals("No hay clientes registrados", tallerVacio.mostrarListaClientes());
    }

    @Test
    void testEliminarCliente() {
        assertTrue(taller.eliminarCliente("1"));
        assertNull(taller.mostrarCliente("1"));
        assertFalse(taller.eliminarCliente("9"));
    }

    @Test
    void testMostrarMecanico() {
        assertNotNull(taller.mostrarMecanico("1"));
        assertEquals("Pedro", taller.mostrarMecanico("1").getNombre());
        assertNull(taller.mostrarMecanico("9"));
    }

    @Test
    void testMostrarListMecanico() {
        String lista = taller.mostrarListMecanico();
        assertTrue(lista.contains("Pedro"));
    }

    @Test
    void testActualizarMecanico() {
        assertTrue(taller.actualizarMecanico("Pedro Mod", "1", false));
        assertEquals("Pedro Mod", taller.mostrarMecanico("1").getNombre());
        assertFalse(taller.mostrarMecanico("1").estaDisponible());
        
        assertFalse(taller.actualizarMecanico("No existe", "9", true));
    }

    @Test
    void testEliminarMecanico() {
        assertTrue(taller.eliminarMecanico("1"));
        assertNull(taller.mostrarMecanico("1"));
        assertFalse(taller.eliminarMecanico("9"));
    }

    @Test
    void testRegistrarBicicletaYMecanico() {
        Cliente cliente = taller.mostrarCliente("1");
        assertTrue(taller.registrarBicicleta("Trek", "Azul", "2", 2, TipoBicicleta.MTB, cliente));
        
        assertTrue(taller.registrarMecanico("Juan", "2", true));
        assertFalse(taller.registrarMecanico("Juan 2", "2", true));
    }

    @Test
    void testBuscarOrden() {
        assertEquals(0, taller.buscarOrdenByCodigo("1"));
        assertEquals(-1, taller.buscarOrdenByCodigo("9"));
    }

    @Test
    void testActualizarOrden() {
        assertTrue(taller.actualizarOrden("1", "Mant", "Diag", EstadoOrden.EN_PROCESO));
        Orden orden = taller.getListOrdenes().get(0);
        assertEquals("Mant", orden.getMotivoServicio());
        assertEquals(EstadoOrden.EN_PROCESO, orden.getEstado());
    }

    @Test
    void testEliminarOrden() {
        assertTrue(taller.eliminarOrden("1"));
        assertEquals(-1, taller.buscarOrdenByCodigo("1"));
        assertFalse(taller.eliminarOrden("9"));
    }

    @Test
    void testAgregarYMostrarTareaAOrden() {
        assertTrue(taller.agregarTareaAOrden("1", "T1", "Desc", 10));
        assertFalse(taller.agregarTareaAOrden("9", "T1", "Desc", 10));
        
        String tareas = taller.mostrarTareasDeOrden("1");
        assertTrue(tareas.contains("T1"));
    }

    @Test
    void testActualizarTareaEnOrden() {
        taller.agregarTareaAOrden("1", "T1", "Desc", 10);
        assertTrue(taller.actualizarTareaEnOrden("1", "T1", "T1 Mod", "Desc", 20));
        
        Orden orden = taller.getListOrdenes().get(0);
        assertEquals(20, orden.getListTareas().get(0).getCosto());
    }

    @Test
    void testEliminarTareaDeOrden() {
        taller.agregarTareaAOrden("1", "T1", "Desc", 10);
        assertTrue(taller.eliminarTareaDeOrden("1", "T1"));
        assertFalse(taller.eliminarTareaDeOrden("1", "T1"));
    }

    @Test
    void testAgregarYMostrarRepuestoAOrden() {
        assertTrue(taller.agregarRepuestoAOrden("1", "R1", 1, 5));
        String repuestos = taller.mostrarRepuestosDeOrden("1");
        assertTrue(repuestos.contains("R1"));
    }

    @Test
    void testActualizarRepuestoEnOrden() {
        taller.agregarRepuestoAOrden("1", "R1", 1, 5);
        assertTrue(taller.actualizarRepuestoEnOrden("1", "R1", "R1 Mod", 2, 8));
        
        Orden orden = taller.getListOrdenes().get(0);
        assertEquals(8, orden.getListRepuestos().get(0).getCosto());
    }

    @Test
    void testEliminarRepuestoDeOrden() {
        taller.agregarRepuestoAOrden("1", "R1", 1, 5);
        assertTrue(taller.eliminarRepuestoDeOrden("1", "R1"));
    }

    @Test
    void testVerHistorialBicicleta() {
        ArrayList<Orden> historial = taller.verHistorialBicicleta("1");
        assertEquals(1, historial.size());
        assertEquals("1", historial.get(0).getCodigo());
    }

    @Test
    void testConsultarOrdenesPorDia() {
        ArrayList<Orden> ordenesHoy = taller.consultarOrdenesPorDia(LocalDate.now());
        assertEquals(1, ordenesHoy.size());
        
        ArrayList<Orden> ordenesAyer = taller.consultarOrdenesPorDia(LocalDate.now().minusDays(1));
        assertEquals(0, ordenesAyer.size());
    }

    @Test
    void testAplicarDescuentoFrecuente_SinDescuento() {
        Orden orden = taller.getListOrdenes().get(0);
        taller.agregarTareaAOrden("1", "T1", "Desc", 100);
        
        int costoFinal = taller.aplicarDescuentoFrecuente(orden);
        assertEquals(100, costoFinal);
    }

    @Test
    void testAplicarDescuentoFrecuente_ConDescuento() {
        Orden orden = taller.getListOrdenes().get(0);
        taller.agregarTareaAOrden("1", "T1", "Desc", 100);
        
        Bicicleta bici = orden.getTheBicicleta();
        bici.agregarAlHistorial(new Orden("2", null, null, null, null, null, null, null, null, null));
        bici.agregarAlHistorial(new Orden("3", null, null, null, null, null, null, null, null, null));
        bici.agregarAlHistorial(new Orden("4", null, null, null, null, null, null, null, null, null));
        
        int costoFinal = taller.aplicarDescuentoFrecuente(orden);
        assertEquals(85, costoFinal);
    }
}
