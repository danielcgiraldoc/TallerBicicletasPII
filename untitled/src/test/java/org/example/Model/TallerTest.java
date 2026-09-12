package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class TallerTest {

    private Taller taller;

    @BeforeEach
    void setUp() {
        taller = new Taller("12", "Super Bicis", "Cra 50");
    }

    @Test
    void testRegistrarCliente() {
        assertTrue(taller.registrarCliente("Maria", "1010", "3200000000", "Calle 10"));
        assertFalse(taller.registrarCliente("Maria 2", "1010", "3333", "Otra")); // Duplicado
    }

    @Test
    void testBuscarCliente() {
        taller.registrarCliente("Maria", "1010", "320", "Dir");
        assertEquals(0, taller.buscarClienteById("1010"));
        assertEquals(-1, taller.buscarClienteById("9999"));
    }

    @Test
    void testActualizarCliente() {
        taller.registrarCliente("Maria", "1010", "320", "Dir");
        assertTrue(taller.actualizarCliente("Maria Gomez", "1010", "300111"));
        assertEquals("Maria Gomez", taller.mostrarCliente("1010").getNombre());
    }

    @Test
    void testRegistrarBicicleta() {
        Cliente cliente = new Cliente("Luis", "2020", "123", "Dir");
        taller.addCliente(cliente);
        
        assertTrue(taller.registrarBicicleta("Giant", "Blanco", "G123", LocalDate.now(), TipoBicicleta.URBANA, cliente));
        assertFalse(taller.registrarBicicleta("Giant", "Blanco", "G123", LocalDate.now(), TipoBicicleta.URBANA, cliente)); // Duplicado
    }

    @Test
    void testRegistrarMecanico() {
        assertTrue(taller.registrarMecanico("Pedro", "M001", true));
        assertFalse(taller.registrarMecanico("Pablo", "M001", true)); // Duplicado
    }
    
    @Test
    void testCrearOrdenDeServicio() {
        Cliente cliente = new Cliente("Luis", "2020", "123", "Dir");
        taller.addCliente(cliente);
        taller.registrarBicicleta("Giant", "Blanco", "G123", LocalDate.now(), TipoBicicleta.URBANA, cliente);
        taller.registrarMecanico("Pedro", "M001", true);
        
        assertTrue(taller.crearOrdenDeServicio("O1", LocalDate.now(), LocalTime.now(), "Revision", "Todo bien", "G123", "M01"));
        assertFalse(taller.crearOrdenDeServicio("O1", LocalDate.now(), LocalTime.now(), "Rev", "Mal", "G123", "M01"));
    }
}
