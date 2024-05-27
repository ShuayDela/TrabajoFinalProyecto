package Plantas;

public class Nuez extends Plantas{
    /**
     * Constructor de la clase Nuez hereda de plantas
     * @param fila posicionamiento
     * @param columna posicionamiento
     */
    public Nuez(int fila,int columna){
        super (fila,columna);
        logo = "🥚";
        soles = 150;
        hp = 20;
        danyo = 0;
    }

}
