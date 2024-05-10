package Juego;

import Plantas.Plantas;
import Utilidades.Util;
import Plantas.*;
import Zombies.*;
import Plantas.Lanzaguisantes;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {

    private int filas;
    private int columnas;
    public String [][] tablamapa;
    private ArrayList<Plantas> plantas = new ArrayList<>();
    private ArrayList<Zombies> zombies = new ArrayList<>();
    int turno;


    public Mapa (){
        filas = 5;
        columnas = 13;
        this.tablamapa = new String[getFilas()][getColumnas()];
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
                for (int k = 0; k<plantas.size() && !esPlanta; k++){
                    Plantas actual = plantas.get(k);
                        if (actual.getFila() == i && actual.getColumna() == j){
                            System.out.print("  ");
                            System.out.print(actual.getLogo());
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

    public void quitarPlanta(int fila , int columna) {
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
    public void moverZombies (){
        for (int i = 0; i< zombies.size(); i++){
            Zombies actual = zombies.get(i);
            int nuevaColumna = actual.getColumna()-1;
            if (nuevaColumna < 0){
                zombies.remove(i);
            }
            else {
                actual.setColumna(nuevaColumna);
            }

        }
    }
    public void generarZombie(){
        int numaleatorio = Util.obtenerEnteroAleatorio(0,4);
        Zombies zombi = new Znormal(numaleatorio,getColumnas());
        zombies.add (zombi);
    }
    public void pasarturno(){
        generarZombie();
        moverZombies();
        for (Plantas planta : plantas){
            planta.atacar(zombies);
        }
        revisarZombies();
    }
    public void revisarZombies (){
        for (int i = 0; i < zombies.size(); i++){
            Zombies actual = zombies.get(i);
                if (actual.getHp() == 0){
                    actual.setVivo(false);
                }
                if (actual.getHp() <= -1){
                    zombies.remove(actual);
                }
            }
        }

    public void añadirPlanta (Plantas nuevo){
        plantas.add(nuevo);
    }



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


}

