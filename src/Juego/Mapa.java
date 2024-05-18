package Juego;

import Plantas.Plantas;
import Utilidades.Util;
import Plantas.*;
import Zombies.*;
import Plantas.Lanzaguisantes;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    private int soles;
    private int filas;
    private int columnas;
    public String [][] tablamapa;
    private ArrayList<Plantas> plantas = new ArrayList<>();
    private ArrayList<Zombies> zombies = new ArrayList<>();
    private int turno;


    public Mapa (){
        filas = 5;
        columnas = 13;
        this.tablamapa = new String[getFilas()][getColumnas()];
        turno = 1;
        soles = 0;
        inicializarMapa();
    }
    public String [][] inicializarMapa(){
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                tablamapa[i][j] = "-";
            }
        }
        return tablamapa;
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
                                System.out.print("~");
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
                                System.out.print("X");
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
                tablamapa[fila][columna] = "-";
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
                if (actual.getHp() == 0){
                    actual.setVivo(false);
                }
                if (actual.getHp() <= 0){
                    zombies.remove(actual);
                }
            }
        }
    public void revisarPlantas(){ //funcion principal que revisa si las plantas estan vivas o no
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getHp() == 0){
                actual.setVivo(false);
            }
            if (actual.getHp() <= 0){
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
        plantas.add(nuevo);
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
}

