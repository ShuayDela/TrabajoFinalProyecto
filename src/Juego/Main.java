package Juego;
import Plantas.*;
import Plantas.Plantas;
import Utilidades.Util;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main (String [] args){
        Scanner teclado = new Scanner(System.in);
        Mapa mapa = new Mapa();
        boolean finalizarjuego = false;
        do {
            mapa.imprimirMapa();
            Menu(teclado, mapa);
        } while (!finalizarjuego);
    }
    public static void Menu (Scanner scanner, Mapa mapa){ //Menu donde se desarrolla el juego.
        boolean finalizarMenu = false;
        do {
            int soles = mapa.getSoles();
            int turno = mapa.getTurno();
            System.out.println("Tienes " + soles + " soles disponibles // turno : " + turno);
            System.out.println("¿Que quieres hacer?");
            System.out.println("1- Plantar una planta");
            System.out.println("2- Quitar una planta");
            System.out.println("3- Pasar turno");
            int opcion = Util.Leernumero(scanner , 1,3);
            if (opcion == 1){
                System.out.println("¿Que planta quieres plantar?");
                System.out.println("1- Lanzaguisantes (100 soles)");
                System.out.println("2- Girasol (50 soles)");
                int opcion2 = Util.Leernumero(scanner,1,2);
                System.out.println();
                if (opcion2 ==1  || opcion2 == 2 ){
                    int fila = Util.LeernumeroFila(scanner);
                    int columna = Util.Leernumerocolumna(scanner);
                    if (opcion2 == 1){
                        Plantas lanza = new Lanzaguisantes(fila,columna);
                        mapa.añadirPlanta(lanza);
                    } else if (opcion2 == 2) {
                        Plantas girasol = new Girasol(fila,columna,turno);
                        mapa.añadirPlanta(girasol);
                    }
                }
            }
            if(opcion == 2){
                System.out.println("Elige la posicion de la planta que quieras quitar: ");
                int fila = Util.LeernumeroFila(scanner);
                int columna = Util.Leernumerocolumna(scanner);
                mapa.quitarPlanta(fila,columna);
            }
            if (opcion == 3){
                System.out.println("Has pasado de turno!");
                mapa.pasarturno();
                finalizarMenu = false;
            }
        }while (finalizarMenu);
    }


}

