package org.example.Model;

import java.util.ArrayList;

public interface IHistoriable {

    void agregarAlHistorial(Orden orden);
    ArrayList<Orden> getHistorial();

}
