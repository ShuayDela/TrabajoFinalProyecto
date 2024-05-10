package Zombies;

public abstract class Zombies {
    protected String logo;
    protected boolean vivo;
    protected int fila;
    protected int columna;
    public int hp;
    protected int daño;

    public Zombies (int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        vivo = true;
    }
    public void atacar (){

    }
    abstract void mover();

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
