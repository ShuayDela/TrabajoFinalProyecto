/**
 * Clase principal que contiene el juego
 */
package Juego;

import Plantas.*;
import Utilidades.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // declaración de variables
        String espaciado = "-----------------------------------------";
        Scanner teclado = new Scanner(System.in);
        String nombre;

        // bienvenida al juego y selección de nombre de jugador
        System.out.println("Elige tu nombre como jugador");
        nombre = teclado.nextLine();
        System.out.println("¡BIENVENIDO A PLANTS VS ZOMBIES!");
        System.out.println("Iniciando menú\n");

        //menú principal
        boolean finalizarMenu = false;
        do {
            Mapa mapa = new Mapa(); //Inicializamos mapa / juego para las siguientes opciones del menu

            // mostrar opciones del menu
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1-Jugar al juego");
            System.out.println("2-Ver estadísticas");
            System.out.println("3-Manual");
            System.out.println("4-Cambiar nombre");
            System.out.println("5-Salir");

            // leer la opcion
            int opcion = Util.Leernumero(teclado, 1, 5);
            System.out.println(espaciado);

            if (opcion == 1) {
                // configuración del nombre del jugador y selección de dificultad
                mapa.setNombre(nombre);
                mapa.seleccionarDificultad();
                // inicio del juego
                mapa.Menu(teclado);
                finalizarMenu = false;
                System.out.println(espaciado);
            } else if (opcion == 2) {
                // ver estadisticas
                try (FileReader fr = new FileReader("estadistica.txt");
                     BufferedReader lector = new BufferedReader(fr)) {
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }
                System.out.println(espaciado);
            } else if (opcion == 3) {
                // ver el manual del juego
                System.out.println("1- Introducción del juego");
                System.out.println("2- Qué hace cada planta/zombie");
                int opcion4 = Util.Leernumero(teclado, 0, 2);
                if (opcion4 == 1) {
                    // mostrar introduccion al juego
                    System.out.println("Este es un juego Plants vs Zombies basado en turnos, donde cada turno salen zombies y se te otorgan soles por cada turno.");
                    System.out.println("Para seleccionar una posición de la planta que quieras plantar/quitar, primero debes ingresar la fila y luego la columna.");
                    System.out.println("Siempre puedes salir de un menú seleccionando la opción 'SALIR' o ingresando [0].");
                    System.out.println("Habrá distintas dificultades que podrás elegir antes de empezar el juego, donde cada una tendrá una probabilidad mayor de aparición de zombies más difíciles.");
                    System.out.println("A continuación se muestran las probabilidades de zombies por nivel:");
                    System.out.println("FACIL:");
                    System.out.println("- Probabilidades:");
                    System.out.println("  * Caracubo: 60%");
                    System.out.println("  * Caracono: 30%");
                    System.out.println("  * Zombie normal: 10%");
                    System.out.println(espaciado);
                    System.out.println("NORMAL:");
                    System.out.println("- Probabilidades:");
                    System.out.println("  * Caracubo: 33,33%");
                    System.out.println("  * Caracono: 33,33%");
                    System.out.println("  * Zombie normal: 33,33%");
                    System.out.println(espaciado);
                    System.out.println("DIFICIL:");
                    System.out.println("- Probabilidades:");
                    System.out.println("  * Caracubo: 60%");
                    System.out.println("  * Caracono: 30%");
                    System.out.println("  * Zombie normal: 10%");
                    System.out.println(espaciado);
                    System.out.println("De momento esto es una demo de un desarrollador humilde");
                    System.out.println(espaciado);
                    finalizarMenu = false;
                } else if (opcion4 == 2) {
                    // mostrar descripción de plantas y zombies
                    System.out.println("PLANTAS");
                    System.out.println(espaciado);
                    System.out.println("EL LANZAGUISTANTES");
                    System.out.println("El Lanzaguisantes es la primera planta de ataque de Plants vs. Zombies. Su costo es de 100 soles y dispara un guisante cada turno infligiendo 1 de daño.");
                    System.out.println(espaciado);
                    System.out.println("EL GIRASOL");
                    System.out.println("El Girasol es otro de las principales plantas de PlantsVSZombies, es una planta que, si bien tiene poca vida, da 50 soles por cada 2 turnos.");
                    System.out.println(espaciado);
                    System.out.println("LA NUEZ");
                    System.out.println("La Nuez es el último integrante en esta demo de Plants vs. Zombies. Es un tanque que, si bien no ataca a los zombies, aguanta mucho ya que tiene mucha vida.");
                    System.out.println(espaciado);
                    System.out.println("ZOMBIES");
                    System.out.println(espaciado);
                    System.out.println("ZNORMAL");
                    System.out.println("El zombie normal de toda la vida.");
                    System.out.println(espaciado);
                    System.out.println("CARACUBO");
                    System.out.println("El mítico zombie con un cubo que le proporciona más resistencia, en esta demo además le hemos añadido más daño.");
                    System.out.println(espaciado);
                    System.out.println("CARACONO");
                    System.out.println("Menos resistente que el caracubo y con menos daño.");
                } else if (opcion4 == 0) {
                    continue;
                }
            } else if (opcion == 4) {
                // cambiar el nombre del jugador
                System.out.println("Pon el nuevo nombre:");
                teclado.next();
                nombre = teclado.nextLine();
                mapa.setNombre(nombre);
                System.out.println(espaciado);
            } else if (opcion == 5){
                System.out.println("Gracias por jugar");
                break;
            }
        } while (!finalizarMenu);
    }
}

