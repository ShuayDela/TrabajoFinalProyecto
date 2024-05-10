package Utilidades;

import java.util.Scanner;

public class Util {
    public static int Leernumero(Scanner scanner,int inicio, int fina) {
        int numero = 0;
        boolean numerodistinto = false;
        do {
            if(scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero <= fina && numero >= inicio) {
                    numerodistinto = false;
                } else {
                    System.out.println("Error tienes que escribir un numero del 1 al 10");
                    numerodistinto = true;
                }

            }
            else {
                System.out.println("ERROR TIENES QUE ESCRIBIR UN NUMERO");
                scanner.next();
                numerodistinto = true;
            }
            } while (numerodistinto);
        return numero;
    }
    public static int LeernumeroFila (Scanner scanner){
        System.out.println("Elige la fila que quieras: ");
        int fila = Leernumero(scanner,1,5);
        return fila -1 ;
    }
    public static int Leernumerocolumna (Scanner scanner){
        System.out.println("Elige la columna que quieras: ");
        int columna = Leernumero(scanner,1,10);
        return columna - 1;
    }
    public static int obtenerEnteroAleatorio(int min, int max) {
        int aleatorioDefinitivo = (int) (Math.random() * (max - min + 1) + min);
        return aleatorioDefinitivo;
    }
}
