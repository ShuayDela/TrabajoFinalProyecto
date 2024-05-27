package Plantas;

import Zombies.Zombies;

import java.util.ArrayList;

public abstract class Plantas {
    protected boolean vivo; // si la planta esta viva
    protected String logo; // lo que representa a la planta
    protected int fila; // posicionamiento
    protected int columna; // posicionamiento
    protected int soles; // los soles que cuesta la planta
    protected int hp; // la vida de la planta
    protected int danyo; // el daño que hace la planta
    protected String logomuerto; // lo que representa a la planta cuando este muerta
    /**
     * Constructor de la clase Plantas
     * @param fila La fila en la que se colocará la planta
     * @param columna La columna en la que se colocará la planta
     */
    public Plantas (int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        vivo = true;
        logomuerto = "❎"; // el logo que aparecera si la planta esta muerta
    }
    /**
     * Realiza un ataque a los zombies en el mapa
     * @param zombies La lista de zombies presentes en el mapa
     */
    public void atacar (ArrayList<Zombies> zombies){
        Zombies zombieMasCercano = null;
        for (int i = 0; i< zombies.size(); i++){
            Zombies actual = zombies.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() > this.columna && actual.getVivo()){ // si el zombie esta en la misma fila y mas lejos de la columna en la que esta la planta y vivo
                if (zombieMasCercano == null || actual.getColumna() < zombieMasCercano.getColumna()){ // se actualizara con el zombiemascercano
                    zombieMasCercano = actual;
                }
            }
        }
        if (zombieMasCercano != null){
            zombieMasCercano.setHp(zombieMasCercano.getHp() - this.danyo); // hara el daño que tenga la planta al zombie mas cercano
        }
    }
    public String getLogomuerto() {
        return logomuerto;
    }

    public void setLogomuerto(String logomuerto) {
        this.logomuerto = logomuerto;
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


    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

}

