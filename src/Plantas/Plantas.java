public abstract class Plantas {
    protected int fila;
    protected int columna;
    protected int soles;
    protected int hp;

    public Plantas (int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }
    abstract void atacar ();
}

