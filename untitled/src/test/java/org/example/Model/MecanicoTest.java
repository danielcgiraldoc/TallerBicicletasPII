package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MecanicoTest {

    private Mecanico mecanico;

    @BeforeEach
    void setUp() {
        mecanico = new Mecanico("Andres", "1", true);
        mecanico.setListOrdenes(new ArrayList<>());
    }

    @Test
    void testCrearMecanico() {
        assertEquals("Andres", mecanico.getNombre());
        assertEquals("M01", mecanico.getCodigo());
        assertTrue(mecanico.estaDisponible());
    }

    @Test
    void testActualizarDisponibilidad() {
        Orden orden = new Orden("O1", null, null, null, null, null, null, EstadoOrden.EN_PROCESO, null, null);
        mecanico.getListOrdenes().add(orden);
        
        mecanico.actualizarDisponibilidad();
        assertFalse(mecanico.estaDisponible());

        orden.setEstado(EstadoOrden.FINALIZADO);
        mecanico.actualizarDisponibilidad();
        assertTrue(mecanico.estaDisponible());
    }
}
