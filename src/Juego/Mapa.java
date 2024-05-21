package Juego;

import Plantas.Plantas;
import Utilidades.Util;
import Plantas.*;
import Zombies.*;
import Plantas.Lanzaguisantes;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    public int soles;
    public int filas;
    public int columnas;
    public ArrayList<Plantas> plantas = new ArrayList<>();
    public ArrayList<Zombies> zombies = new ArrayList<>();
    public int turno;


    public Mapa (){
        filas = 5;
        columnas = 13;
        turno = 1;
        soles = 0;

    }
    public void imprimirMapa (){
        System.out.println("═══════════════════════════");
        for (int i = 0; i<getFilas(); i++){
            for (int j = 0; j < getColumnas(); j++){
                boolean esPlanta = false;
                for (int k = 0; k<plantas.size() && !esPlanta; k++){ //Imprime en la tabla segun lo que sea, si es planta pone el LOGO de la planta , si es Zombie pone el logo del zombie y si no es ninguno pues un vacio
                    Plantas actual = plantas.get(k);
                        if (actual.getFila() == i && actual.getColumna() == j){
                            System.out.print("  ");
                            if(actual.isVivo()) {
                                System.out.print(actual.getLogo());
                            }
                            if (!actual.isVivo()){
                                System.out.println(actual.getLogomuerto());
                            }
                            esPlanta = true;
                        }
                }
                if (!esPlanta){
                    boolean esZombi = false;
                    for (int o = 0 ; o <zombies.size () && !esZombi; o++){
                        Zombies actual = zombies.get(o);
                        if (actual.getFila() == i && actual.getColumna() == j){
                            System.out.print("  ");
                            if (actual.getVivo()) {
                                System.out.print(actual.getLogo());
                            }
                            if (!actual.getVivo()){
                                System.out.println(actual.getLogomuerto());
                            }
                            esZombi = true;
                        }
                    }
                    if (!esZombi){
                        System.out.print("  ");
                        System.out.print("-");
                    }
                }
            }
            System.out.println();
            System.out.println("═══════════════════════════");
        }
    }
    public void quitarPlanta(int fila , int columna) { //funcion para quitar una planta
    boolean encontrado = false;
        for (int i = 0; i< plantas.size() && !encontrado; i++){
        Plantas planta = plantas.get(i);
            if (planta.getFila() == fila && planta.getColumna() == columna ){
                plantas.remove(i);
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("No hay ninguna planta en esa posicion");
        }
        else if (encontrado){
            System.out.println("¡Se ha quitado la planta con exito!");
        }
    }
    public void moverZombies (){ //funcion principal donde los zombies se mueven sin embargo si detecta otro Zombie o una planta no podra moverse.
        for (int i = 0; i< zombies.size() ; i++){
            Zombies actual = zombies.get(i);
            int nuevaColumna = actual.getColumna()-1;
            if (nuevaColumna < 0){
                zombies.remove(i);
                i--;
            }
            boolean hayOtroZ = false;
            for (int j = 0; j< zombies.size() && !hayOtroZ; j++){
                Zombies otro = zombies.get(j);
                if (otro.getFila() == actual.getFila() && otro.getColumna() == actual.getColumna() -1 ){
                    hayOtroZ = true;
                }
            }
            boolean hayPlanta = false;
            for (int k = 0 ; k< plantas.size() && !hayPlanta ; k++){
                Plantas otraplanta = plantas.get(k);
                if (otraplanta.getFila() == actual.getFila() && otraplanta.getColumna() == actual.getColumna() - 1){
                    hayPlanta = true;
                }
            }
            if (!hayOtroZ && !hayPlanta){
                actual.setColumna(nuevaColumna);
            }
        }

    }
    public void generarZombie(){ //funcion principal para el cambio de turno donde se generan zombies aleatoriamente (cuando añada mas zombies añadire una probabilidad de que zombiess pueden entrar)

        int numaleatorio = Util.obtenerEnteroAleatorio(0,4);
        Zombies zombi = new Znormal(numaleatorio,getColumnas());
        zombies.add (zombi);
    }
    public void pasarturno(){ //funcion principal para que al pasar de turno se hagan las demas funciones principales para el progreso lineal del juego.
        generarZombie();
        moverZombies();
        for (Plantas planta : plantas){
            planta.atacar(zombies);
        }
        for (Zombies zombi : zombies){
            zombi.atacar(plantas);
        }
        revisarZombies();
        revisarPlantas();
        generarSoles();
        turno++;
    }
    public void revisarZombies (){ // funcion principal que revisa si los zombies estan vivos o no
        for (int i = 0; i < zombies.size(); i++){
            Zombies actual = zombies.get(i);
                if (actual.getHp() <= 0){
                    actual.setVivo(false);
                }
                if (!actual.isVivo()){
                    zombies.remove(actual);
                }
            }
        }
    public void revisarPlantas(){ //funcion principal que revisa si las plantas estan vivas o no
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getHp() <= 0){
                actual.setLogo("❎");
                actual.setVivo(false);
            }
            if (!actual.isVivo()){
                plantas.remove(actual);
            }
        }
    }
    public void generarSoles (){
        int solesturno = 0;
        if (turno % 2 == 0 | turno == 1){
            solesturno = 25;
        }
        int solesturnogirasol = 0;
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual instanceof Girasol){
                solesturnogirasol += ((Girasol) actual).generarSoles(turno);
            }
        }
        this.soles = soles + solesturno + solesturnogirasol;
    }
    public void añadirPlanta (Plantas nuevo){
        boolean existe = false;
        for (int i = 0; i < plantas.size() && !existe; i++){
            Plantas actualplanta = plantas.get(i);
            if (actualplanta.getFila() == nuevo.getFila() && actualplanta.getColumna() == nuevo.getColumna()){
                existe = true;
            }
        }
        for (int i = 0; i< zombies.size() && !existe; i++){
            Zombies actualzombie = zombies.get(i);
            if(actualzombie.getFila() == nuevo.getFila() && actualzombie.getColumna() == nuevo.getColumna()){
                existe = true;
            }
        }
        if (existe){
            System.out.println("No se puede añadir ya que esa posicion esta ocupada");
        }
        if (!existe){
            plantas.add(nuevo);
        }
    } //funcion basica que añade las plantas


    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    public void Menu (Scanner scanner){ //Menu donde se desarrolla el juego.
        boolean finalizarMenu = false;
        do {
            int soles = this.soles;
            int turno = this.turno;
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
                if (opcion2 ==1  || opcion2 == 2 ){
                    int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                    if (filacolumna == null){
                        continue;
                    }
                    int fila = filacolumna[0];
                    int columna = filacolumna[1];
                    if (opcion2 == 1){
                        Plantas lanza = new Lanzaguisantes(fila,columna);
                        añadirPlanta(lanza);
                    } else if (opcion2 == 2) {
                        Plantas girasol = new Girasol(fila,columna,turno);
                        añadirPlanta(girasol);
                    }
                }
            }
            if(opcion == 2){
                int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                if (filacolumna == null){
                    continue;
                }
                int fila = filacolumna[0];
                int columna = filacolumna[1];
                quitarPlanta(fila,columna);
            }
            if (opcion == 3){
                System.out.println("Has pasado de turno!");
                pasarturno();
                finalizarMenu = false;
            }
        }while (finalizarMenu);
    }
}

