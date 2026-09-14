package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OrdenTest {

    private Orden orden;

    @BeforeEach
    void setUp() {
        Bicicleta bici = new Bicicleta("Specialized", "Azul", "S001", 3, TipoBicicleta.RUTA);
        orden = new Orden("ORD-1", LocalDate.now(), LocalTime.now(), "Ruidos", "Revision", bici, new ArrayList<>(), EstadoOrden.RECIBIDO, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testCrearOrden() {
        assertEquals("ORD-1", orden.getCodigo());
        assertEquals(EstadoOrden.RECIBIDO, orden.getEstado());
    }

    @Test
    void testCalcularCostoTotal() {
        orden.getListTareas().add(new Tarea("Ajuste", "Ajuste general", 20000));
        orden.getListRepuestos().add(new Repuesto("Freno", 1, 30000, null));
        
        assertEquals(50000, orden.calcularCostoTotal());
    }
}
