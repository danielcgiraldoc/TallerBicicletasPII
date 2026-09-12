package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TareaTest {

    private Tarea tarea;

    @BeforeEach
    void setUp() {
        tarea = new Tarea("Pintura", "Pintar marco", 120000);
    }

    @Test
    void testCrearTarea() {
        assertEquals("Pintura", tarea.getNombre());
        assertEquals("Pintar marco", tarea.getDescripcion());
        assertEquals(120000, tarea.getCosto());
    }

    @Test
    void testSetters() {
        tarea.setCosto(130000);
        assertEquals(130000, tarea.getCosto());
    }
}
