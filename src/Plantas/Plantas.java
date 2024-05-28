package Plantas;

import Juego.Personajes;
import Zombies.Zombies;

import java.util.ArrayList;

public abstract class Plantas extends Personajes {

    protected int soles; // los soles que cuesta la planta

    /**
     * Constructor de la clase Plantas hereda los atributos de la clase personajes
     *
     * @param fila    La fila en la que se colocará la planta
     * @param columna La columna en la que se colocará la planta
     */
    public Plantas(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        vivo = true;
        logomuerto = "x"; // el logo que aparecera si la planta esta muerta
    }

    /**
     * Realiza un ataque a los zombies en el mapa
     *
     * @param zombies La lista de zombies presentes en el mapa
     */
    public void atacar(ArrayList<Zombies> zombies) {
        Zombies zombieMasCercano = null;
        for (int i = 0; i < zombies.size(); i++) {
            Zombies actual = zombies.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() > this.columna && actual.isVivo()) { // si el zombie esta en la misma fila y mas lejos de la columna en la que esta la planta y vivo
                if (zombieMasCercano == null || actual.getColumna() < zombieMasCercano.getColumna()) { // se actualizara con el zombiemascercano
                    zombieMasCercano = actual;
                }
            }
        }
        if (zombieMasCercano != null) {
            zombieMasCercano.setHp(zombieMasCercano.getHp() - this.danyo); // hara el daño que tenga la planta al zombie mas cercano
        }
    }

    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }
}


