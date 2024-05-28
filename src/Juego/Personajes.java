package Juego;
/**
 * Clase Personjes es los atributos que tienen en comun todos los personajes del juego
 * No inicializa nada solo pasa los atributos a sus hijos
 */
abstract public class Personajes {
    protected String logo; // lo que representa al personaje ya sea zombie o planta
    protected boolean vivo; // si el zombie esta vivo
    protected int fila; // el posicionamiento del personaje
    protected int columna; // el posicionamiento del personaje
    protected int hp; // los puntos de vida del personaje
    protected int danyo; // el daño que hace el personaje
    protected String logomuerto; // el simbolo de cuando el personaje este muerto

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

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }

    public String getLogomuerto() {
        return logomuerto;
    }

    public void setLogomuerto(String logomuerto) {
        this.logomuerto = logomuerto;
    }
}
