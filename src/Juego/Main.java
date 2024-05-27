package Juego;
import Plantas.*;
import Utilidades.Util;
import Juego.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main (String [] args){
        Scanner teclado = new Scanner(System.in);
        String nombre ;
        System.out.println("Elige tu nombre como jugador");
        nombre = teclado.nextLine();
        System.out.println("¡ BIENVENIDO A PLANTS VS ZOMBIES !");
        System.out.println("Iniciando menu");
        System.out.println();
        boolean finalizarMenu = false;
        do{
            Mapa mapa = new Mapa();
            System.out.println("¿Que quieres hacer?");
            System.out.println("1-Jugar al juego");
            System.out.println("2-Ver estadisticas");
            System.out.println("3-Cargas partida");
            System.out.println("4-Manual");
            int opcion = Util.Leernumero(teclado,1,5);
            if (opcion == 1)
            {
                mapa.setNombre(nombre);
                boolean finalizarjuego = false;
                mapa.seleccionarDificultad();
                do {
                    mapa.Menu(teclado);
                    finalizarjuego = mapa.isFinalizarjuego();
                } while (!finalizarjuego);
                finalizarMenu = false;
            }
            if (opcion == 2){

            }
            if (opcion == 3){

            }
            if(opcion == 4){
                System.out.println("1- Introcciones del juego");
                System.out.println("2- Que hace cada planta/zombie");
                int opcion4 = Util.Leernumero(teclado,1,2);
                if (opcion4 == 1){
                    System.out.println("Este es un juego Plantas vs Zombies basado en turnos por cada turno saldran zombies y se te ortorgara soles por cada turno");
                    System.out.println("Para seleccionar una posicion de la planta que quieras plantas / quitar tendras que poner primero la fila y luego la columna de tal manera que seria 1,5 siendo 1 la fila y 5 la columna");
                    System.out.println("Habran distintas dificultades que podras elegir antes de empezar el juego dependiendo de la dificultad incrementara el numero de zombies que hay que matar para ganar y aumentara la probabilidad de aparicion de zombies mas dificiles");
                    System.out.println("A continuacion te mostrare las probabilidades y el numero de zombies por nivel");
                    System.out.println("Facil || Numero : 50 || Probabilidades - Caracubo 10% - Caracono 10% - Zombie normal 80%");
                    System.out.println("Medio || Numero : 100 || Probabilidades - Caracubo 33,33% - Caracono 33,33% - Zombie normal 33,33%");
                    System.out.println("Dificil || Numero : 200 || Probabilidades - Caracubo 60% - Caracono 30% - Zombie normal 10%");
                    System.out.println("Personalizado || Numero : El que quieras (si pones 0 es ilimitado) || Probabilidades - Lo que elijas");
                    System.out.println("De momento esto es una demo de un desarrollador humilde");
                    finalizarMenu = true;
                }
                if (opcion4 == 2){
                    System.out.println("EL LANZAGUISTANTES");
                    System.out.println("El Lanzaguisantes es la primera planta de ataque de Plants vs. Zombies. Su costo es de 100 soles y dispara un guisante cada turno infligiendo 1 de daño.");
                    //Seguir describiendo a los zombies / plantas luego
                }

            }
            if (opcion == 5){
                System.out.println("Saliendo del juego , Gracias por jugar");
            }
        } while (finalizarMenu);

    }
}


