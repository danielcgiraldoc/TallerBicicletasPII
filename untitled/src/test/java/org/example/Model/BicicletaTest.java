package org.example.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BicicletaTest {

    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        bicicleta = new Bicicleta("Trek", "Rojo", "SN123", 4, TipoBicicleta.MTB);
    }

    @Test
    void testCrearBicicleta() {
        assertEquals("Trek", bicicleta.getMarca());
        assertEquals("Rojo", bicicleta.getColor());
        assertEquals("SN123", bicicleta.getSerial());
        assertEquals(TipoBicicleta.MTB, bicicleta.getTipoBicicleta());
    }

    @Test
    void testHistorialServicios() {
        Orden orden = new Orden("O001", LocalDate.now(), null, "Falla", "Revision", bicicleta, null, EstadoOrden.RECIBIDO, null, null);
        bicicleta.agregarAlHistorial(orden);
        
        assertEquals(1, bicicleta.getHistorial().size());
        assertEquals("O001", bicicleta.getHistorial().get(0).getCodigo());
    }
}
