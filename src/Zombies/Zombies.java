package Zombies;
import Plantas.*;
import java.util.ArrayList;

public abstract class Zombies {
    protected String logo; // lo que representa al zombi
    protected boolean vivo; // si el zombie esta vivo
    protected int fila; // posicionamiento
    protected int columna; // posicionamiento
    protected int hp; // los puntos de vida del zombie
    protected int danyo; // el daño que hace el zombie
    protected String logomuerto; // el simbolo del zombie cuando este muerto
    /**
     * Constructor de la clase Zombies
     * @param fila La fila en la que se coloca el zombie
     * @param columna La columna en la que se coloca el zombie
     */
    public Zombies (int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        vivo = true;
        logomuerto = "X";
    }
    /**
     * Método que representa el ataque del zombie a las plantas
     * @param plantas La lista de plantas en el juego
     */
    public void atacar (ArrayList<Plantas> plantas){
        for (int i = 0; i < plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() == (this.columna-1) && actual.isVivo()){ // si la planta esta en la misma fila y columna a la izquierda del zombie la ataca
                actual.setHp(actual.getHp() - this.danyo); // le baja la vida a dicha planta
            }
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    public boolean getVivo (){
        return vivo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
