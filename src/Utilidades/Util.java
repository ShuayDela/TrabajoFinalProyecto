package Utilidades;

import java.util.Scanner;
/**
 * La clase Util proporciona métodos de utilidad para realizar operaciones comunes.
 * Incluye métodos para leer números desde la entrada estándar, obtener un número aleatorio
 * dentro de un rango específico, y leer una posición de fila y columna.
 */
public class Util {
    /**
     * Lee un número entero desde la entrada estándar dentro de un rango especificado.
     * @param scanner  para leer la entrada del usuario
     * @param inicio El valor mínimo aceptado para el número
     * @param fina El valor máximo aceptado para el número
     * @return Devuelve el numero que ha elegido el usuario entre esos de los parametros
     */
    public static int Leernumero(Scanner scanner,int inicio, int fina) {
        int numero = 0;
        boolean numerodistinto = false;
        do {
            if(scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero <= fina && numero >= inicio) {
                    numerodistinto = false;
                } else {
                    System.out.println("Error tienes que escribir un numero del " +inicio +   " al " + fina);
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
    /**
     * Lee una posición de fila y columna desde la entrada estándar
     * @param scanner El objeto Scanner utilizado para leer la entrada del usuario
     * @return Devuelve una array con dos numeros el de columna [1] y el de fila [0]
     */
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
                    numeros[0] = Integer.parseInt(partesdelnumero[0].trim()) - 1 ;
                    numeros[1] = Integer.parseInt(partesdelnumero[1].trim()) ;
                    if (numeros[0] >= 0 && numeros[0] < 5 && numeros[1] >= 1 && numeros[1] < 13) {
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
    /**
     * Crea un número entero aleatorio dentro de un rango especificado
     * @param min El valor mínimo
     * @param max El valor máximo
     * @return El número entero aleatorio que se haya creado
     */
    public static int obtenerEnteroAleatorio(int min, int max) {
        int aleatorioDefinitivo = (int) (Math.random() * (max - min + 1) + min);
        return aleatorioDefinitivo;
    }
}
