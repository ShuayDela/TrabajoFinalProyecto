package Zombies;
import Juego.Personajes;
import Plantas.*;
import java.util.ArrayList;

public abstract class Zombies extends Personajes {
    /**
     * Constructor de la clase Zombies hereda los atributos de la clase personajes
     *
     * @param fila    La fila en la que se coloca el zombie
     * @param columna La columna en la que se coloca el zombie
     */
    public Zombies(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        vivo = true;
        logomuerto = "X";
    }

    /**
     * Método que representa el ataque del zombie a las plantas
     *
     * @param plantas La lista de plantas en el juego
     */
    public void atacar(ArrayList<Plantas> plantas) {
        for (int i = 0; i < plantas.size(); i++) {
            Plantas actual = plantas.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() == (this.columna - 1) && actual.isVivo()) { // si la planta esta en la misma fila y columna a la izquierda del zombie la ataca
                actual.setHp(actual.getHp() - this.danyo); // le baja la vida a dicha planta
            }
        }
    }
}
