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
                    System.out.println("Error tienes que escribir un numero del " +inicio +   "al " + fina);
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
    public static int [] LeerNumeroFilaColumna (Scanner scanner){
        boolean numerocorrecto = false;
        int[] numeros = null;
        do {
            System.out.println("Ingresa la posicion [0 cancelar]:");
            String entrada = scanner.next();
            if (entrada.trim().equals("0")){
                return null;
            }
            String[] partesdelnumero = entrada.split(",");
            if (partesdelnumero.length == 2) {
                try {
                    numeros = new int [2];
                    numeros[0] = Integer.parseInt(partesdelnumero[0].trim()) - 1;
                    numeros[1] = Integer.parseInt(partesdelnumero[1].trim()) - 1;
                    if (numeros[0] >= 0 && numeros[0] < 5 && numeros[1] >= 0 && numeros[1] < 13) {
                        numerocorrecto = true;
                    }
                    else{
                        System.out.println("Error en la posicion de la fila");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: ingresa numeros");
                }
            }
            else {
                System.out.println("Error: Ingresa dos numeros que esten separados por ','");
            }
        } while (!numerocorrecto);
        return numeros;
    }
    public static int LeernumeroFila (Scanner scanner){
        System.out.println("Elige la fila que quieras: ");
        int fila = Leernumero(scanner,1,5);
        return fila -1 ;
    }
    public static int Leernumerocolumna (Scanner scanner){
        System.out.println("Elige la columna que quieras: ");
        int columna = Leernumero(scanner,1,9);
        return columna - 1;
    }
    public static int obtenerEnteroAleatorio(int min, int max) {
        int aleatorioDefinitivo = (int) (Math.random() * (max - min + 1) + min);
        return aleatorioDefinitivo;
    }
}
