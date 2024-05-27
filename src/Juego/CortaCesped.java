package Juego;
import Zombies.*;

import java.util.ArrayList;

public class CortaCesped {
    private boolean activado; // Para saber si el cortacesped ha sido activado o no
    private int columna; //Posicionamiento
    private int fila; //Posicionamientop
    /**
     * Contructor del cortacesped se inicializa activado por defecto.
     *
     * @param columna su posicionamiento en columna.
     * @param fila su posicionamiento en fila
     */
    public CortaCesped(int columna,int fila){
        this.columna = columna;
        this.fila = fila;
        activado = true;
    }

    /**
     * Activa la carretilla eliminando todos los zombies en la misma fila
     *
     * @param listazombies La lista de zombies a eliminar.
     */
    public void activarcarretilla(ArrayList<Zombies> listazombies) {
        ArrayList<Zombies> zombiesAEliminar = new ArrayList<>(); // Nueva ArrayList para almacenar los zombies a eliminar (Esto para evitar el problema de ConcurrentModificationException)
        for (Zombies zombi : listazombies) { //Recorremos la lista
            if (fila == zombi.getFila()) {
                zombiesAEliminar.add(zombi); // Agregamos el zombie a la lista de zombies a eliminar
            }
        }
        listazombies.removeAll(zombiesAEliminar); //Eliminamos todos los zombies
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
