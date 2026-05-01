package Ejercicio16;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio16 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.print("Introduce la ruta: ");
        String ruta = sc.nextLine();
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("No existe.");
            return;
        }

        int op = 0;
        do {
            System.out.println("1. Informacion");
            System.out.println("2. Mostrar archivo");
            System.out.println("3. Añadir contenido");
            System.out.println("4. Borrar archivo");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            op = Integer.parseInt(sc.nextLine());

            if (op == 1) {
                informacion(archivo);
            } else if (op == 2) {
                mostrar(archivo);
            } else if (op == 3) {
                añadir(archivo);
            } else if (op == 4) {
                borrar(archivo);
            } else if (op == 5) {
                System.out.println("Saliendo");
            } else {
                System.out.println("Opcion no valida.");
            }
        } while (op != 5);
    }

    public static void informacion(File archivo) {
        if (archivo.isFile()) {
            System.out.println("Nombre: " + archivo.getName());
            System.out.println("Directorio: " + archivo.getParent());
        } else {
            File[] archivos = archivo.listFiles();
            if (archivos == null || archivos.length == 0) {
                System.out.println(" esta vacio el directorio.");
            } else {
                for (int i = 0; i < archivos.length; i++) {
                    if (archivos[i].isFile()) {
                        System.out.println(archivos[i].getName() + " - " + archivos[i].length() + " bytes");
                    }
                }
            }
        }
    }

    public static void mostrar(File archivo) throws Exception {
        if (!archivo.isFile()) {
            System.out.println("esto no es un archivo");
            return;
        }
        Scanner f = new Scanner(archivo);
        while (f.hasNext()) {
            String linea = f.nextLine();
            System.out.println(linea);
        }
        f.close();
    }

    public static void añadir(File archivo) throws Exception {
        if (archivo.isDirectory()) {
            System.out.println("Es un directorio .");
            return;
        }
        System.out.print("Texto: ");
        String texto = sc.nextLine();
        PrintWriter f = new PrintWriter(new FileWriter(archivo, true));
        f.println(texto);
        f.close();
        System.out.println("Añadido.");
    }

    public static void borrar(File archivo) {
        if (!archivo.isFile()) {
            System.out.println("No es un archivo.");
            return;
        }
        System.out.print("¿Borrar " + archivo.getName() + "? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equals("s")) {
            archivo.delete();
            System.out.println("borrado.");
        } else {
            System.out.println("cancelado.");
        }
    }
}