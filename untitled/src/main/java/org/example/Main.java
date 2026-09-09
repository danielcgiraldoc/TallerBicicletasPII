package org.example;

import org.example.Model.Cliente;
import org.example.Model.Taller;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Taller taller = new Taller("1090", "Taller", "Cra 15");
        Cliente cliente = new Cliente("Daniel", "38", "317", "Cra20");
        taller.addCliente(cliente);
        System.out.println("" + taller.getListClientes());
    }
}