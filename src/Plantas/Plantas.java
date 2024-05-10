package Plantas;

import Zombies.Zombies;

import java.util.ArrayList;

public abstract class Plantas {
    protected String logo;
    protected int fila;
    protected int columna;
    protected int soles;
    protected int hp;
    protected int daño;

    public Plantas (int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getLogo() {
        return logo;
    }
    public void atacar (ArrayList<Zombies> zombies){
        Zombies zombieMasCercano = null;
        for (int i = 0; i< zombies.size(); i++){
            Zombies actual = zombies.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() > this.columna && actual.getVivo()){
            if (zombieMasCercano == null || actual.getColumna() < zombieMasCercano.getColumna()){
                zombieMasCercano = actual;
            }
            }
        }
        if (zombieMasCercano != null){
            zombieMasCercano.setHp(zombieMasCercano.getHp() - this.daño);
        }
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    abstract void imprimir ();
}

