package Ejercicio12;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
       Ejercicio12 ventana=new Ejercicio12();

        // Propiedades del formulario en el main, setVisible al final 
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300);
        ventana.setVisible(true);
    }
}
