package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan Perez", "2", "000", "Calle 123");
    }

    @Test
    void testCrearCliente() {
        assertEquals("Juan Perez", cliente.getNombre());
        assertEquals("2", cliente.getId());
        assertEquals("000", cliente.getTelefono());
        assertEquals("Calle 123", cliente.getDireccion());
        assertNotNull(cliente.getListBicicletas());
    }

    @Test
    void testSettersCliente() {
        cliente.setNombre("Carlos");
        cliente.setId("999");
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("999", cliente.getId());
    }
}
