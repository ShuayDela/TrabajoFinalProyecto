package Plantas;

public class Lanzaguisantes extends Plantas {
    /**
     * Constructor de la clase Lanzaguisantes hereda de plantas
     * @param fila posicionamiento
     * @param columna posicionamiento
     */
    public Lanzaguisantes(int fila,int columna){
        super (fila,columna);
        logo = "L";
        soles = 100;
        hp = 5;
        danyo = 1;
    }
}
