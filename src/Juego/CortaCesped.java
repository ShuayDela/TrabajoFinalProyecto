package Juego;
import Zombies.*;

import java.util.ArrayList;

public class CortaCesped {
    boolean activado;
    private int columna;
    private int fila;
    public CortaCesped(int columna,int fila){
        this.columna = columna;
        this.fila = fila;
        activado = true;
    }
    public void activarcarretilla(ArrayList<Zombies> listazombies) {
        ArrayList<Zombies> zombiesAEliminar = new ArrayList<>();
        for (Zombies zombi : listazombies) {
            if (fila == zombi.getFila()) {
                zombiesAEliminar.add(zombi);
            }
        }
        listazombies.removeAll(zombiesAEliminar);
    }


    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }
}
