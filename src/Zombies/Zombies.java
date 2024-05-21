package Zombies;
import Plantas.*;
import java.util.ArrayList;

public abstract class Zombies {
    protected String logo;
    protected boolean vivo;
    protected int fila;
    protected int columna;
    protected int hp;
    protected int daño;
    protected String logomuerto;

    public String getLogomuerto() {
        return logomuerto;
    }

    public void setLogomuerto(String logomuerto) {
        this.logomuerto = logomuerto;
    }

    public Zombies (int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        vivo = true;
        logomuerto = "❌";
    }
    public void atacar (ArrayList<Plantas> plantas){
        for (int i = 0; i < plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getFila() == this.fila && actual.getColumna() == (this.columna-1) && actual.isVivo()){
                actual.setHp(actual.getHp() - this.daño);
            }
        }
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
