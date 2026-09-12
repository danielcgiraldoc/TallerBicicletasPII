package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepuestoTest {

    private Repuesto repuesto;

    @BeforeEach
    void setUp() {
        repuesto = new Repuesto("Llanta", 2, 50000, null);
    }

    @Test
    void testCrearRepuesto() {
        assertEquals("Llanta", repuesto.getNombre());
        assertEquals(2, repuesto.getCantidad());
        assertEquals(50000, repuesto.getCosto());
    }

    @Test
    void testSetters() {
        repuesto.setCosto(60000);
        assertEquals(60000, repuesto.getCosto());
    }
}
