public abstract class Zombies {
    protected int fila;
    protected int columna;
    protected int hp;

    public Zombies (int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
    abstract void mover();

}
